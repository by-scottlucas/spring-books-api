package br.com.spring.books.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.spring.books.exceptions.AlreadyExistsException;
import br.com.spring.books.exceptions.NotFoundException;
import br.com.spring.books.models.Book;
import br.com.spring.books.models.User;
import br.com.spring.books.models.UserBook;
import br.com.spring.books.models.dtos.BookDTO;
import br.com.spring.books.models.dtos.ReadBookDTO;
import br.com.spring.books.repositories.BookRepository;
import br.com.spring.books.repositories.ReadBookRepository;

@Service
public class ReadBookService {

    private final ReadBookRepository readBookRepository;
    private final BookRepository bookRepository;
    private final AuthService authService;

    public ReadBookService(
            ReadBookRepository readBookRepository,
            BookRepository bookRepository,
            AuthService authService
    ) {
        this.readBookRepository = readBookRepository;
        this.bookRepository = bookRepository;
        this.authService = authService;
    }

    public List<UserBook> listReadBooksByAuthenticatedUser() {
        User user = authService.getAuthenticatedUser();
        return user.getBooksRead();
    }

    public UserBook registerBookAsRead(ReadBookDTO data) {
        User user = authService.getAuthenticatedUser();

        Book book = bookRepository
                .findByTitleAndAuthor(data.getTitle(), data.getAuthor())
                .orElseGet(() -> bookRepository.save(data.toBookEntity()));

        boolean alreadyRegistered = readBookRepository
                .findByUserIdAndBookId(user.getId(), book.getId())
                .isPresent();

        if (alreadyRegistered) {
            throw new AlreadyExistsException(
                "Este livro já foi marcado como lido por você."
            );
        }

        UserBook userBook = data.toUserBookEntity(user, book);
        return readBookRepository.save(userBook);
    }

    public UserBook getReadBookDetails(Long bookId) {
        User user = authService.getAuthenticatedUser();
        return readBookRepository.findByUserIdAndBookId(user.getId(), bookId)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado"));
    }

    public UserBook updateReadBook(Long bookId, BookDTO data) {
        User user = authService.getAuthenticatedUser();

        UserBook userBook = readBookRepository.findByUserIdAndBookId(user.getId(), bookId)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado"));

        Book book = userBook.getBook();

        if (data.getTitle() != null)
            book.setTitle(data.getTitle());
        if (data.getAuthor() != null)
            book.setAuthor(data.getAuthor());
        if (data.getTotalPages() != null)
            book.setTotalPages(data.getTotalPages());
        if (data.getDescription() != null)
            book.setDescription(data.getDescription());

        bookRepository.save(book);
        return readBookRepository.save(userBook);
    }

    public void deleteReadBook(Long bookId) {
        User user = authService.getAuthenticatedUser();

        UserBook userBook = readBookRepository.findByUserIdAndBookId(user.getId(), bookId)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado"));

        readBookRepository.delete(userBook);
    }
}