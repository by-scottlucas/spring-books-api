package br.com.spring.mvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String titulo;

    @Column()
    private String autor;

    @Column()
    private String editora;

    @Column()
    private String descricao;

    @Column()
    private String genero;

    @Column()
    private int lancamento;

}
