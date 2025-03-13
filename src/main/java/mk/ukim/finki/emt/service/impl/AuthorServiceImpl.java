package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.Author;
import mk.ukim.finki.emt.model.Country;
import mk.ukim.finki.emt.repository.AuthorRepository;
import mk.ukim.finki.emt.repository.CountryRepository;
import mk.ukim.finki.emt.service.AuthorService;
import mk.ukim.finki.emt.service.CountryService;
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
    public Optional<Author> update(Long id, String name, String surname, Long countryId) {
        Optional<Author> savedAuthor = this.findById(id);
        Optional<Country> country = countryService.findById(countryId);
        savedAuthor.get().setName(name);
        savedAuthor.get().setSurname(surname);
        savedAuthor.get().setCountry(country.get());
        authorRepository.save(savedAuthor.get());
        return savedAuthor;
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Optional<Country> country = countryService.findById(countryId);
        return Optional.of(authorRepository.save(new Author(name, surname, country.get())));
    }

    @Override
    public void delete(Long id) {
        Optional<Author> author = this.findById(id);
        authorRepository.delete(author.get());
    }
}
