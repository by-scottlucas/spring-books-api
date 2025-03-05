package br.com.spring.books.exceptions;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(Long id) {
        super("Livro não contrado.");
    }
}