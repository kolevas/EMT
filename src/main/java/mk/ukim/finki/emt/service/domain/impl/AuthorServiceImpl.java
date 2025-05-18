package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.model.domain.Author;
import mk.ukim.finki.emt.model.domain.Country;
import mk.ukim.finki.emt.model.projections.AuthorName;
import mk.ukim.finki.emt.repository.AuthorRepository;
import mk.ukim.finki.emt.service.domain.AuthorService;
import mk.ukim.finki.emt.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;
    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.of(authorRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public Optional<Author> update(Long id, Author author) {
        Optional<Author> savedAuthor = this.findById(id);
        Optional<Country> country = countryService.findById(author.getCountry().getId());
        savedAuthor.get().setName(author.getName());
        savedAuthor.get().setSurname(author.getSurname());
        savedAuthor.get().setCountry(country.get());
        authorRepository.save(savedAuthor.get());
        return savedAuthor;
    }

    @Override
    public Optional<Author> save(Author author) {
        Optional<Country> country = countryService.findById(author.getCountry().getId());
        return Optional.of(authorRepository.save(new Author(author.getName(), author.getSurname(), country.get())));
    }

    @Override
    public Optional<Author> delete(Long id) {
        Optional<Author> author = this.findById(id);
        authorRepository.delete(author.get());
        return author;
    }

    @Override
    public List<AuthorName> findAllProjectedBy() {
        return authorRepository.findAllProjectedBy();
    }
}
