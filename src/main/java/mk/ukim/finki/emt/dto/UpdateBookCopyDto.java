package mk.ukim.finki.emt.dto;

import mk.ukim.finki.emt.model.domain.Book;
import mk.ukim.finki.emt.model.domain.BookCopy;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateBookCopyDto(Long id, Book book) {

    public static UpdateBookCopyDto from(BookCopy bookCopy) {
        return new UpdateBookCopyDto(bookCopy.getId(), bookCopy.getBook());
    }

    public static List<UpdateBookCopyDto> from(List<BookCopy> bookCopy) {
        return bookCopy.stream().map(UpdateBookCopyDto::from).collect(Collectors.toList());
    }
}
