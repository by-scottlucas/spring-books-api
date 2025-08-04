package br.com.spring.books.controllers;

import java.util.List;

import br.com.spring.books.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.books.models.Book;
import br.com.spring.books.models.dtos.BookDTO;
import br.com.spring.books.services.BookService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> listBooks() {
        return bookService.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book registerBook(@Valid @RequestBody BookDTO book) {
        return bookService.create(book);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) throws NotFoundException {
        return bookService.read(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(
        @PathVariable Long id,
        @Valid @RequestBody BookDTO book
    ) throws NotFoundException {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}