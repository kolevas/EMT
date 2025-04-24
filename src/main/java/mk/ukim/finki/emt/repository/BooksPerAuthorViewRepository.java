package mk.ukim.finki.emt.repository;


import mk.ukim.finki.emt.model.views.BooksPerAuthorView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface BooksPerAuthorViewRepository extends JpaRepository<BooksPerAuthorView, Long> {

    @Query(value="select b.num_books from books_per_author b where b.author_id = :author_id", nativeQuery = true)
    Optional<Integer> numBooksByAuthorId( @Param("author_id") Long authorId);
}
