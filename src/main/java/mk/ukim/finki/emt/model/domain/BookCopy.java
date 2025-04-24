package mk.ukim.finki.emt.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "book_copy")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Book book;
    private Boolean isLoaned;

    public BookCopy() {

    }

    public BookCopy(Book book) {
        this.book = book;
        this.isLoaned = false;
    }

}
