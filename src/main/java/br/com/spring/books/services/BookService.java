package br.com.spring.books.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.spring.books.exceptions.NotFoundException;
import br.com.spring.books.models.Book;
import br.com.spring.books.models.DTO.BookDTO;
import br.com.spring.books.repositories.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> list() {
        return this.bookRepository.findAll();
    }

    public Book create(BookDTO data) {
        Book book = data.toEntity();
        return this.bookRepository.save(book);
    }

    public Book read(Long id) throws NotFoundException {
        return this.bookRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Book update(Long id, BookDTO data) throws NotFoundException {
        return this.bookRepository.findById(id)
                .map(livro -> {
                    livro.setTitle(data.getTitle());
                    livro.setAuthor(data.getAuthor());
                    livro.setPublisher(data.getPublisher());
                    livro.setDescription(data.getDescription());
                    livro.setGenrer(data.getGenrer());
                    livro.setReleaseDate(data.getReleaseDate());

                    return this.bookRepository.save(livro);
                })
                .orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }
}
