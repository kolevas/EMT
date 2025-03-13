package mk.ukim.finki.emt.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String continent;

    public Country(String name , String continent) {
        this.name = name;
        this.continent = continent;
    }

    public Country() {

    }
}
