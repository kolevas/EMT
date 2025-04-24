package mk.ukim.finki.emt.service.application;

import mk.ukim.finki.emt.model.views.AuthorsPerCountryView;

import java.util.Optional;

public interface AuthorsPerCountryViewApplicationService {
    Optional<Integer> authorsPerCountry(Long countryId);
}
