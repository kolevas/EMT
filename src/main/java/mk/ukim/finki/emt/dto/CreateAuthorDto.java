package mk.ukim.finki.emt.dto;

import mk.ukim.finki.emt.model.domain.Author;
import mk.ukim.finki.emt.model.domain.Country;

public record CreateAuthorDto(String name, String surname, Long country) {
    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
}
