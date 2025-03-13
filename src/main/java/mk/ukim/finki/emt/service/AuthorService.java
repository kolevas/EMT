package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> update(Long id, String name, String surname, Long countryId);
    Optional<Author> save(String name, String surname, Long countryId);
    void delete(Long id);
}
