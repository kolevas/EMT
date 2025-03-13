package mk.ukim.finki.emt.web;

import mk.ukim.finki.emt.model.Book;
import mk.ukim.finki.emt.model.BookCopy;
import mk.ukim.finki.emt.model.dto.BookDto;
import mk.ukim.finki.emt.service.BookCopyService;
import mk.ukim.finki.emt.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;
    private final BookCopyService copyService;
    public BookController(BookService bookService, BookCopyService copyService) {
        this.bookService = bookService;
        this.copyService = copyService;
    }

    @GetMapping
    public List<Book> findAll() {
        return this.bookService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


//    Да додава нови книги кои може да се изнајмат

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    Да брише книги кои повеќе не се во добра состојба и нема да се изнајмуваат

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(bookService.findById(id).isPresent()) {
            bookService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

//    Да изменува одреден запис за книга
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id,@RequestBody BookDto bookDto) {
        return bookService.update(id, bookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
//    Да обележи одредена книга како изнајмена

    @PutMapping("/loan/{id}")
    public ResponseEntity<Book> loanBook(@PathVariable Long id) {
        return copyService.loan(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createCopy/{id}")
    public ResponseEntity<Book> createCopy(@PathVariable Long id) {
        return copyService.createCopy(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/bookCopies/{id}")
    public List<BookCopy> findAllCopies(@PathVariable Long id) {
        return this.copyService.findByBook(id);
    }
}
