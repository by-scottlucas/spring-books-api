package br.com.spring.books.models.DTO;

import br.com.spring.books.models.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookDTO {
    
    @NotBlank(message = "O título é obrigatório.")
    private String title;

    @NotBlank(message = "O autor é obrigatório.")
    private String author;

    @NotBlank(message = "A editora é obrigatória.")
    private String publisher;

    @NotBlank(message = "A descrição é obrigatória.")
    private String description;

    @NotBlank(message = "O gênero é obrigatório.")
    private String genrer;

    @NotNull(message = "O ano de lançamento é obrigatório.")
    private Integer releaseDate;

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