package mk.ukim.finki.emt.dto;

import mk.ukim.finki.emt.model.domain.BookHistory;
import mk.ukim.finki.emt.model.domain.Country;
import mk.ukim.finki.emt.model.enumerations.BookCategory;
import mk.ukim.finki.emt.model.domain.Author;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHistoryDto(String name, BookCategory category, Author author) {

    public static DisplayHistoryDto from(BookHistory history) {
        return new DisplayHistoryDto( history.getName(), history.getCategory(), history.getAuthor() );
    }

    public List<DisplayHistoryDto> from(List<BookHistory> countries) {
        return countries.stream().map(DisplayHistoryDto::from).collect(Collectors.toList());
    }
}
