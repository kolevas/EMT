package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.Book;
import mk.ukim.finki.emt.model.dto.BookDto;
import mk.ukim.finki.emt.repository.AuthorRepository;
import mk.ukim.finki.emt.repository.BookCopyRepository;
import mk.ukim.finki.emt.repository.BookRepository;
import mk.ukim.finki.emt.service.AuthorService;
import mk.ukim.finki.emt.service.BookCopyService;
import mk.ukim.finki.emt.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;


    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;

    }

    @Override
    public Optional<Book> save(BookDto book) {

        if (book.getAuthor() != null && authorService.findById(book.getAuthor()).isPresent()) {
            return Optional.of(bookRepository.save(new Book(book.getName(), book.getCategory(), authorService.findById(book.getAuthor()).get())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        Book book = this.findById(id).get();
        if (bookDto.getAuthor() != null) {
            book.setAuthor(authorService.findById(bookDto.getAuthor()).get());
        }
        if (bookDto.getCategory() != null) {
            book.setCategory(bookDto.getCategory());
        }
        if (bookDto.getName() != null) {
            book.setName(bookDto.getName());
        }
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> delete(Long id) {
        Book book = this.findById(id).get();
        this.bookRepository.delete(book);
        return Optional.of(book);
    }

//    @Override
//    public Optional<Book> loanBook(Long id) {
//        Book book = this.findById(id).get();
//        book.setAvailableCopies(book.getAvailableCopies() - 1);
//        this.bookRepository.save(book);
//        return Optional.of(book);
//    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(bookRepository.findById(id).orElseThrow(RuntimeException::new));
    }

//    @Override
//    public void createCopy(Long id) {
//        copyService.save(id);
//    }
}
