package mk.ukim.finki.emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mk.ukim.finki.emt.dto.CreateBookDto;
import mk.ukim.finki.emt.dto.DisplayHistoryDto;
import mk.ukim.finki.emt.dto.UpdateBookCopyDto;
import mk.ukim.finki.emt.dto.UpdateBookDto;
import mk.ukim.finki.emt.model.enumerations.BookCategory;
import mk.ukim.finki.emt.model.domain.BookCopy;
import mk.ukim.finki.emt.repository.BooksPerAuthorViewRepository;
import mk.ukim.finki.emt.service.application.BookApplicationService;
import mk.ukim.finki.emt.service.application.BookCopyApplicationService;
import mk.ukim.finki.emt.service.application.BooksPerAuthorViewApplicationService;
import mk.ukim.finki.emt.service.application.impl.BooksPerAuthorViewApplicationServiceImpl;
import mk.ukim.finki.emt.service.domain.BookCopyService;
import mk.ukim.finki.emt.service.domain.BooksPerAuthorViewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/books")
public class BookController {
    private final BookApplicationService bookService;
    private final BookCopyApplicationService copyService;
    private final BooksPerAuthorViewApplicationService booksPerAuthorViewService;

    public BookController(BookApplicationService bookService, BookCopyApplicationService copyService, BooksPerAuthorViewApplicationService booksPerAuthorViewService) {
        this.bookService = bookService;
        this.copyService = copyService;

        this.booksPerAuthorViewService = booksPerAuthorViewService;
    }


    @GetMapping
    @Operation(summary = "Најди ги сите книги", description = "Го враќа списокот на сите книги.")
    public List<UpdateBookDto> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/history/{id}")
    @Operation(summary = "Историја на книга", description = "Го враќа списокот на сите книги.")
    public List<DisplayHistoryDto> getHistory(@PathVariable Long id) {
        return bookService.getHistory(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Најди книга по ID", description = "Го враќа записот за книгата со даденото ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Книгата е успешно пронајдена"),
            @ApiResponse(responseCode = "404", description = "Книгата не е пронајдена")
    })
    public ResponseEntity<UpdateBookDto> findById(@Parameter(description = "ID на книгата") @PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Додај нова книга", description = "Додава нов запис за книга која може да се изнајмува.")
    public ResponseEntity<UpdateBookDto> addBook(@RequestBody CreateBookDto bookDto) {
        return bookService.save(bookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Избриши книга", description = "Брише запис за книга која повеќе не е во добра состојба.")
    public ResponseEntity<Void> delete(@Parameter(description = "ID на книгата") @PathVariable Long id) {
        if(bookService.findById(id).isPresent()) {
            bookService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Измени запис за книга", description = "Го ажурира записот за дадена книга со нови информации.")
    public ResponseEntity<UpdateBookDto> editBook(@Parameter(description = "ID на книгата") @PathVariable Long id, @RequestBody CreateBookDto bookDto) {
        return bookService.update(id, bookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/loan/{id}")
    @Operation(summary = "Изнајми книга", description = "Означува одредена копија на книга како изнајмена.")
    public ResponseEntity<UpdateBookDto> loanBook(@Parameter(description = "ID на копијата на книгата") @PathVariable Long id, @RequestParam String username) {
        return copyService.loan(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createCopy/{id}")
    @Operation(summary = "Креирај копија на книга", description = "Создава нова копија на дадена книга.")
    public ResponseEntity<UpdateBookCopyDto> createCopy(@Parameter(description = "ID на книгата") @PathVariable Long id) {
        return copyService.createCopy(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/bookCopies/{id}")
    @Operation(summary = "Најди ги сите копии на книга", description = "Го враќа списокот на сите копии на дадена книга.")
    public List<UpdateBookCopyDto> findAllCopies(@Parameter(description = "ID на книгата") @PathVariable Long id) {
        return this.copyService.findByBook(id);
    }

    @GetMapping("/books/by-author")
    public ResponseEntity<Integer> numBooksByAuthor(@RequestParam Long authorId) {
        return booksPerAuthorViewService.numBooksByAuthorId(authorId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/categories")
    public List<String> categories() {
        return Arrays.stream(BookCategory.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
