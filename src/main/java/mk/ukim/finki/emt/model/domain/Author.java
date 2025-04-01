package mk.ukim.finki.emt.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Author {
//    name (String), surname (String), country (Country)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToOne
    private Country country;

    public Author() {

    }
    public Author(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }



}
