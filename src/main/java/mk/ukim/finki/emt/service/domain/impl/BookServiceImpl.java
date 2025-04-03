package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.model.domain.Book;
import mk.ukim.finki.emt.model.domain.BookHistory;
import mk.ukim.finki.emt.repository.BookRepository;
import mk.ukim.finki.emt.service.domain.AuthorService;
import mk.ukim.finki.emt.service.domain.BookService;
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
    public Optional<Book> save(Book book) {

        if (book.getAuthor() != null && authorService.findById(book.getAuthor().getId()).isPresent()) {
            return Optional.of(bookRepository.save(new Book(book.getName(), book.getCategory(), authorService.findById(book.getAuthor().getId()).get())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        Book tmp = bookRepository.findById(id).orElse(null);

        if (tmp == null) {
            return Optional.empty();
        }
        BookHistory copy = new BookHistory(book.getName(), book.getCategory(), book.getAuthor());
        if (book.getAuthor() != null) {
            tmp.setAuthor(authorService.findById(book.getAuthor().getId()).get());

            System.out.println(book.getBookHistory());
        }
        if (book.getCategory() != null) {
            tmp.setCategory(book.getCategory());
        }
        if (book.getName() != null) {
            tmp.setName(book.getName());
        }
        tmp.getBookHistory().add(copy);
        return Optional.of(this.bookRepository.save(tmp));
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

    @Override
    public List<BookHistory> getHistory(Long id) {
        return this.findById(id).get().getBookHistory();
    }

//    @Override
//    public void createCopy(Long id) {
//        copyService.save(id);
//    }
}
