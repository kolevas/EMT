package mk.ukim.finki.emt.service.domain;

import mk.ukim.finki.emt.model.domain.Author;
import mk.ukim.finki.emt.model.projections.AuthorName;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> update(Long id, Author author);
    Optional<Author> save(Author author);
    Optional<Author> delete(Long id);
    List<AuthorName> findAllProjectedBy();
}
