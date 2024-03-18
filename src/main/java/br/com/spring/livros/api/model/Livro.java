package br.com.spring.livros.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    @NotBlank
    @NotEmpty
    private String titulo;

    @Column()
    @NotBlank
    @NotEmpty
    private String autor;

    @Column()
    @NotBlank
    @NotEmpty
    private String editora;

    @Column()
    @NotBlank
    @NotEmpty
    private String descricao;

    @Column()
    @NotBlank
    @NotEmpty
    private String genero;

    @Column()
    @NotNull
    private int lancamento;

}
