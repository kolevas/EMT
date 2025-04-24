package mk.ukim.finki.emt.model.views;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "books_per_author")
@Immutable
@Data
public class BooksPerAuthorView {
    @Id
    private Long authorId;

    private String authorName;

    private int num_books;
}