package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.model.domain.Book;
import mk.ukim.finki.emt.model.domain.BookCopy;
import mk.ukim.finki.emt.repository.BookCopyRepository;
import mk.ukim.finki.emt.service.domain.BookCopyService;
import mk.ukim.finki.emt.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookCopyServiceImpl implements BookCopyService {

    private final BookCopyRepository copyRepository;
    private final BookService bookService;

    public BookCopyServiceImpl(BookCopyRepository bookCopyRepository, BookService bookService) {
        this.copyRepository = bookCopyRepository;
        this.bookService = bookService;
    }

    @Override
    public Optional<BookCopy> createCopy(Long id) {
        Book book = bookService.findById(id).get();
        BookCopy bookCopy = new BookCopy(book);
        copyRepository.save(bookCopy);
        return Optional.of(bookCopy);
    }

    @Override
    public Optional<BookCopy> findById(Long id) {
        return Optional.of(copyRepository.findById(id).get());
    }

    @Override
    public List<BookCopy> findAll() {
        return copyRepository.findAll();
    }

    @Override
    public List<BookCopy> findByBook(Long id) {
        Book book = bookService.findById(id).get();
        return this.findAll().stream().filter(bookCopy -> bookCopy.getBook().equals(book)).collect(Collectors.toList());
    }

    @Override
    public Optional<Book> loan(Long id) {
        BookCopy bookCopy = copyRepository.findById(id).get();
        bookCopy.setIsLoaned(true);
        return Optional.of(bookCopy.getBook());
    }

    @Override
    public Optional<BookCopy> returnBook(Long id) {
        BookCopy bookCopy = copyRepository.findById(id).get();
        bookCopy.setIsLoaned(false);
        return Optional.of(bookCopy);
    }


}
