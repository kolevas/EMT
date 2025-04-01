package mk.ukim.finki.emt.dto;

import mk.ukim.finki.emt.model.domain.Book;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateBookDto(Long Id,
                            String name,
                            String category,
                            Long author) {
    public static UpdateBookDto from(Book book) {
        return new UpdateBookDto(book.getId(), book.getName(), book.getCategory().name(), book.getAuthor().getId());
    }

    public static List<UpdateBookDto> from(List<Book> all) {
        return all.stream().map(UpdateBookDto::from).collect(Collectors.toList());
    }
}
