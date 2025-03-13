package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.Book;
import mk.ukim.finki.emt.model.BookCopy;
import mk.ukim.finki.emt.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookCopyService {
    Optional<Book> createCopy(Long id);
    Optional<BookCopy> findById(Long id);
    List<BookCopy> findAll();
    List<BookCopy> findByBook(Long id);
    Optional<Book> loan(Long id);
    public Optional<BookCopy> returnBook(Long id);
}
