package mk.ukim.finki.emt.dto;

import mk.ukim.finki.emt.model.domain.Author;
import mk.ukim.finki.emt.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateAuthorDto(Long id, String name, String surname, Country country) {

    public static UpdateAuthorDto from(Author author) {

        return new UpdateAuthorDto(author.getId(), author.getName(), author.getSurname(), author.getCountry());

    }
    public static List<UpdateAuthorDto> from(List<Author> all) {

        return all.stream().map(UpdateAuthorDto::from).collect(Collectors.toList());
    }
}
