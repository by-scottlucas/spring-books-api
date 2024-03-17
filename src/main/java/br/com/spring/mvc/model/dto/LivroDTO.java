package br.com.spring.mvc.model.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LivroDTO {

    @NotBlank
    @Length(min = 10)
    private String titulo;

    @NotBlank
    @Length(min = 10)
    private String autor;

    @NotBlank
    @Length(min = 10)
    private String editora;

    @NotBlank
    @Length(min = 10)
    private String descricao;

    @NotBlank
    @Length(min = 10)
    private String genero;

    @NotBlank
    @Length(min = 4)
    private int lancamento;

}
