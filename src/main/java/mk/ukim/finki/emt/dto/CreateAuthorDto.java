package mk.ukim.finki.emt.dto;

import mk.ukim.finki.emt.model.domain.Author;
import mk.ukim.finki.emt.model.domain.Country;

public record CreateAuthorDto(String name, String surname, Country country) {
    public Author toAuthor() {
        return new Author(name, surname, country);
    }
}
