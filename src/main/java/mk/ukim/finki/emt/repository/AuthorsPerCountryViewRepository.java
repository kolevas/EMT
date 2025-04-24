package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.views.AuthorsPerCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorsPerCountryViewRepository extends JpaRepository<AuthorsPerCountryView, Long> {

    @Query(value="select b.author_count from authors_per_country b where b.author_count = :country_id", nativeQuery = true)
    Optional<Integer> authorsPerCountry(@Param("country_id") Long countryId);
}
