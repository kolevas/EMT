package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.CreateBookDto;
import mk.ukim.finki.emt.dto.DisplayHistoryDto;
import mk.ukim.finki.emt.dto.UpdateBookDto;
import mk.ukim.finki.emt.model.domain.Author;
import mk.ukim.finki.emt.model.domain.Book;
import mk.ukim.finki.emt.model.enumerations.BookCategory;
import mk.ukim.finki.emt.service.application.BookApplicationService;
import mk.ukim.finki.emt.service.domain.AuthorService;
import mk.ukim.finki.emt.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public Optional<UpdateBookDto> save(CreateBookDto bookDto) {
        Optional<Author> author = authorService.findById(bookDto.author());
        BookCategory bookCategory = BookCategory.valueOf(bookDto.category());
        return bookService.save(bookDto.toBook(bookCategory,author.get())).map(UpdateBookDto::from);
    }

    @Override
    public Optional<UpdateBookDto> update(Long id, CreateBookDto bookDto) {
        Optional<Author> author = authorService.findById(bookDto.author());
        BookCategory bookCategory = BookCategory.valueOf(bookDto.category());
        return bookService.update(id,bookDto.toBook(bookCategory,author.get())).map(UpdateBookDto::from);
    }

    @Override
    public Optional<UpdateBookDto> delete(Long id) {
        return bookService.delete(id).map(UpdateBookDto::from);
    }

    @Override
    public List<UpdateBookDto> findAll() {
        return bookService.findAll().stream().map(UpdateBookDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<UpdateBookDto> findById(Long id) {
        return bookService.findById(id).map(UpdateBookDto::from);
    }

    @Override
    public List<DisplayHistoryDto> getHistory(Long id) {
        return bookService.getHistory(id).stream().map(DisplayHistoryDto::from).collect(Collectors.toList());
    }
}
