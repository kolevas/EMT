package mk.ukim.finki.emt.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.model.enumerations.BookCategory;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {
//    id (Long), name (String), category (enum), author (Author), availableCopies (Integer)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private BookCategory category;
    @ManyToOne
    private Author author;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BookHistory> bookHistory;

    public Book() {

        bookHistory = new ArrayList<>();
    }

    public Book( String name, BookCategory category, Author author) {
        this.name = name;
        this.category = category;
        this.author = author;
        bookHistory = new ArrayList<>();
    }

}
