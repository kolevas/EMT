package mk.ukim.finki.emt.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.model.enumerations.BookCategory;

@Data
@Entity
@Table(name = "book_history")
public class BookHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BookCategory category;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public BookHistory() {
    }

    public BookHistory(String name, BookCategory category, Author author) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.author = author;
    }
}
