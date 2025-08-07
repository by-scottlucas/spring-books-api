package br.com.spring.books.models.dtos;

import java.time.LocalDate;

import br.com.spring.books.models.Book;
import br.com.spring.books.models.User;
import br.com.spring.books.models.UserBook;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReadBookDTO {

    @NotBlank(message = "O título é obrigatório.")
    private String title;

    @NotBlank(message = "O autor é obrigatório.")
    private String author;

    @NotNull(message = "O total de páginas é obrigatório.")
    private Integer totalPages;

    @NotBlank(message = "A descrição é obrigatória.")
    private String description;

    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long userId;

    private LocalDate readAt;
    
    private boolean finished = true;

    public Book toBookEntity() {
        Book book = new Book();
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setTotalPages(this.totalPages);
        book.setDescription(this.description);
        return book;
    }

    public UserBook toUserBookEntity(User user, Book book) {
        UserBook userBook = new UserBook();
        userBook.setUser(user);
        userBook.setBook(book);
        userBook.setReadAt(this.readAt != null ? this.readAt : LocalDate.now());
        userBook.setFinished(this.finished);
        return userBook;
    }
}
