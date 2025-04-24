package mk.ukim.finki.emt.service.domain;

import java.util.Optional;

public interface AuthorsPerCountryViewService {
    Optional<Integer> authorsPerCountry(Long countryId);
}
