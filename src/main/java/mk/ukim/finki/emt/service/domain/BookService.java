package mk.ukim.finki.emt.service.domain;

import mk.ukim.finki.emt.model.domain.Book;
import mk.ukim.finki.emt.model.domain.BookHistory;

import java.util.List;
import java.util.Optional;


public interface BookService {
    public Optional<Book> save(Book Book);
    public Optional<Book> update(Long id, Book Book);
    public Optional<Book> delete(Long id);
//    public Optional<Book> loanBook(Long id);
    public List<Book> findAll();
    public Optional<Book> findById(Long id);
//    public void createCopy(Long id);
    public List<BookHistory> getHistory(Long id);

}
