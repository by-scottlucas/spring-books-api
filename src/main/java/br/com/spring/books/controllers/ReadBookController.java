package br.com.spring.books.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.books.exceptions.NotFoundException;
import br.com.spring.books.models.UserBook;
import br.com.spring.books.models.dtos.BookDTO;
import br.com.spring.books.models.dtos.ReadBookDTO;
import br.com.spring.books.services.ReadBookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/books/read")
public class ReadBookController {

    private final ReadBookService readBookService;

    public ReadBookController(ReadBookService readBookService) {
        this.readBookService = readBookService;
    }

    @GetMapping
    public List<UserBook> listReadBooks() {
        return readBookService.listReadBooksByAuthenticatedUser();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserBook registerReadBook(@Valid @RequestBody ReadBookDTO data) {
        return readBookService.registerBookAsRead(data);
    }

    @GetMapping("/{bookId}")
    public UserBook getReadBookDetails(@PathVariable Long bookId) throws NotFoundException {
        return readBookService.getReadBookDetails(bookId);
    }

    @PutMapping("/{bookId}")
    public UserBook updateReadBook(
            @PathVariable Long bookId,
            @Valid @RequestBody BookDTO data) throws NotFoundException {
        return readBookService.updateReadBook(bookId, data);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReadBook(@PathVariable Long bookId) throws NotFoundException {
        readBookService.deleteReadBook(bookId);
    }
}