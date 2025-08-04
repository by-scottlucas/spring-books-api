package br.com.spring.books.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.books.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{}
