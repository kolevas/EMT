package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.repository.AuthorsPerCountryViewRepository;
import mk.ukim.finki.emt.service.domain.AuthorsPerCountryViewService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorsPerCountryViewServiceImpl implements AuthorsPerCountryViewService {

    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;

    public AuthorsPerCountryViewServiceImpl(AuthorsPerCountryViewRepository authorsPerCountryViewRepository) {
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
    }


    @Override
    public Optional<Integer> authorsPerCountry(Long countryId) {
        return authorsPerCountryViewRepository.authorsPerCountry(countryId);
    }
}
