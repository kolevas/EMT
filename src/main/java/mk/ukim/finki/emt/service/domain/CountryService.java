package mk.ukim.finki.emt.service.domain;

import mk.ukim.finki.emt.model.domain.Country;

import java.util.List;
import java.util.Optional;


public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(Country country);
    Optional<Country> update(Long id, Country country);
    Optional<Country> delete(Long id);

}
