package br.com.spring.books.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.books.models.UserBook;

@Repository
public interface ReadBookRepository extends JpaRepository<UserBook, Long> {
    Optional<UserBook> findByUserIdAndBookId(Long userId, Long bookId);
} 
