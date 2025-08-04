package br.com.spring.books.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.spring.books.exceptions.AlreadyExistsException;
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
        Optional<Book> existingBook = bookRepository
                .findByTitleAndAuthor(data.getTitle(), data.getAuthor());

        if (existingBook.isPresent()) {
            throw new AlreadyExistsException("Este Livro já foi cadastrado.");
        }

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
                .map(book -> {
                    book.setTitle(data.getTitle());
                    book.setAuthor(data.getAuthor());
                    book.setPublisher(data.getPublisher());
                    book.setDescription(data.getDescription());
                    book.setGenrer(data.getGenrer());
                    book.setReleaseDate(data.getReleaseDate());

                    return this.bookRepository.save(book);
                })
                .orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(Long id) throws NotFoundException {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        this.bookRepository.deleteById(id);
    }
}
