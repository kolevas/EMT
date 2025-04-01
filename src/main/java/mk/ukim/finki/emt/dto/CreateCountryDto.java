package mk.ukim.finki.emt.dto;

import mk.ukim.finki.emt.model.domain.Country;

public record CreateCountryDto(String name , String continent) {
    public Country toCountry() {
        return new Country(name, continent);
    }
}
