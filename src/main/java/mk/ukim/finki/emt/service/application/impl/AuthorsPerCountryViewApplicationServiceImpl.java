package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.repository.AuthorsPerCountryViewRepository;
import mk.ukim.finki.emt.service.application.AuthorsPerCountryViewApplicationService;
import mk.ukim.finki.emt.service.domain.AuthorsPerCountryViewService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorsPerCountryViewApplicationServiceImpl implements AuthorsPerCountryViewApplicationService {

    private final AuthorsPerCountryViewService authorsPerCountryViewService;

    public AuthorsPerCountryViewApplicationServiceImpl(AuthorsPerCountryViewService authorsPerCountryViewService) {
        this.authorsPerCountryViewService = authorsPerCountryViewService;
    }

    @Override
    public Optional<Integer> authorsPerCountry(Long countryId) {
        return authorsPerCountryViewService.authorsPerCountry(countryId);
    }
}
