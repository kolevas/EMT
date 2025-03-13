package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.Book;
import mk.ukim.finki.emt.model.dto.BookDto;

import java.util.List;
import java.util.Optional;


public interface BookService {
    public Optional<Book> save(BookDto bookDto);
    public Optional<Book> update(Long id, BookDto bookDto);
    public Optional<Book> delete(Long id);
//    public Optional<Book> loanBook(Long id);
    public List<Book> findAll();
    public Optional<Book> findById(Long id);
//    public void createCopy(Long id);
}
