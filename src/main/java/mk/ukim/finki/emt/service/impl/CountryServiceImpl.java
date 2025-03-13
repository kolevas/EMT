package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.Country;
import mk.ukim.finki.emt.repository.CountryRepository;
import mk.ukim.finki.emt.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.of(countryRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        return Optional.of(countryRepository.save(new Country(name, continent)));
    }

    @Override
    public Optional<Country> update(Long id, String name, String continent) {
        Optional<Country> countryToUpdate = this.findById(id);
        countryToUpdate.get().setName(name);
        countryToUpdate.get().setContinent(continent);
        return Optional.of(countryRepository.save(countryToUpdate.get()));
    }

    @Override
    public void delete(Long id) {
        Optional<Country> country = this.findById(id);
        countryRepository.delete(country.get());
    }
}
