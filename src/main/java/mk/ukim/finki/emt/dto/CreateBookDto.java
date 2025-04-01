package mk.ukim.finki.emt.dto;

import mk.ukim.finki.emt.model.domain.Author;
import mk.ukim.finki.emt.model.domain.Book;
import mk.ukim.finki.emt.model.enumerations.BookCategory;

public record CreateBookDto(String name,
                            String category,
                            Long author) {

    public  Book toBook(BookCategory category1, Author author1) {
        return new Book(name,category1,author1);
    }
}
