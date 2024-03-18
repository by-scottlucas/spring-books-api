package br.com.spring.livros.api.model.dto;

import br.com.spring.livros.api.model.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LivroDTO {

    @NotBlank
    @NotEmpty
    private String titulo;

    @NotBlank
    @NotEmpty
    private String autor;

    @NotBlank
    @NotEmpty
    private String editora;

    @NotBlank
    @NotEmpty
    private String descricao;

    @NotBlank
    @NotNull
    private String genero;

    @NotNull
    private int lancamento;

    public Livro toEntity() {
        Livro livro = new Livro();
        livro.setTitulo(getTitulo());
        livro.setAutor(getAutor());
        livro.setEditora(getEditora());
        livro.setDescricao(getDescricao());
        livro.setGenero(getGenero());
        livro.setLancamento(getLancamento());

        return livro;
    }

}
