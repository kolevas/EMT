package mk.ukim.finki.emt.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.model.enumerations.BookCategory;

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

    public Book() {

    }

    public Book( String name, BookCategory category, Author author) {
        this.name = name;
        this.category = category;
        this.author = author;
    }

}
