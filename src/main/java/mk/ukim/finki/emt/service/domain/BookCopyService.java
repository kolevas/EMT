package mk.ukim.finki.emt.service.domain;

import mk.ukim.finki.emt.model.domain.Book;
import mk.ukim.finki.emt.model.domain.BookCopy;

import java.util.List;
import java.util.Optional;

public interface BookCopyService {
    Optional<BookCopy> createCopy(Long id);
    Optional<BookCopy> findById(Long id);
    List<BookCopy> findAll();
    List<BookCopy> findByBook(Long id);
    Optional<Book> loan(Long id);
    public Optional<BookCopy> returnBook(Long id);
}
