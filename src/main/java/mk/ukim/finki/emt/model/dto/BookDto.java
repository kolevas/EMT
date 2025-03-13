package mk.ukim.finki.emt.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mk.ukim.finki.emt.model.BookCategory;


@Data
public class BookDto {
    private String name;
    private BookCategory category;
    @NotNull
    private Long author;
    private Integer availableCopies;

    public BookDto(String name, BookCategory category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
    public BookDto() {}
}
