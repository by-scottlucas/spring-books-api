package br.com.spring.books.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.books.models.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}
