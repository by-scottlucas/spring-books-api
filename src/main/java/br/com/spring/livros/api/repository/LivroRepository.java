package br.com.spring.livros.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.livros.api.model.Livro;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    
}
