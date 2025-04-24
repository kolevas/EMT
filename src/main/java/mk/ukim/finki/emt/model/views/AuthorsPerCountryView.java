package mk.ukim.finki.emt.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "authors_per_country")
@Immutable
@Data
public class AuthorsPerCountryView {
    @Id
    private Long countryId;

    private String countryName;

    private Long authorCount;
}
