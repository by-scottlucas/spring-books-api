package br.com.spring.books.models.DTO;

import br.com.spring.books.models.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookDTO {
    @NotBlank
    @NotEmpty
    private String title;

    @NotBlank
    @NotEmpty
    private String author;

    @NotBlank
    @NotEmpty
    private String publisher;

    @NotBlank
    @NotEmpty
    private String description;

    @NotBlank
    @NotNull
    private String genrer;

    @NotNull
    private int releaseDate;

    public Book toEntity() {
        Book book = new Book();
        book.setTitle(getTitle());
        book.setAuthor(getAuthor());
        book.setPublisher(getPublisher());
        book.setDescription(getDescription());
        book.setGenrer(getGenrer());
        book.setReleaseDate(getReleaseDate());

        return book;
    }
}
