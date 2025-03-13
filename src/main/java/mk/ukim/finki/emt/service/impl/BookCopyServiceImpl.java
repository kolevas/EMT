package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.Book;
import mk.ukim.finki.emt.model.BookCopy;
import mk.ukim.finki.emt.model.dto.BookDto;
import mk.ukim.finki.emt.repository.BookCopyRepository;
import mk.ukim.finki.emt.service.BookCopyService;
import mk.ukim.finki.emt.service.BookService;
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
    public Optional<Book> createCopy(Long id) {
        Book book = bookService.findById(id).get();
        copyRepository.save(new BookCopy(book));
        return Optional.of(book);
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
