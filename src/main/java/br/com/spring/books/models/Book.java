package br.com.spring.books.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    @NotBlank
    @NotEmpty
    private String title;

    @Column()
    @NotBlank
    @NotEmpty
    private String author;

    @Column()
    @NotBlank
    @NotEmpty
    private String publisher;

    @Column()
    @NotBlank
    @NotEmpty
    private String description;

    @Column()
    @NotBlank
    @NotEmpty
    private String genrer;

    @Column()
    @NotNull
    private int releaseDate;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBook> readers;
}
