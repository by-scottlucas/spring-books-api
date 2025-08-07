package br.com.spring.books.models.dtos;

import br.com.spring.books.models.Book;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookDTO {

    @NotBlank(message = "O título é obrigatório.")
    private String title;

    @NotBlank(message = "O autor é obrigatório.")
    private String author;

    @NotBlank(message = "O total de páginas é obrigatório.")
    private Integer totalPages;

    @NotBlank(message = "A descrição é obrigatória.")
    private String description;

    public Book toEntity() {
        Book book = new Book();
        book.setTitle(getTitle());
        book.setAuthor(getAuthor());
        book.setTotalPages(getTotalPages());
        book.setDescription(getDescription());
        return book;
    }
}