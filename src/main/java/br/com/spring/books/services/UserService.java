package br.com.spring.books.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.spring.books.exceptions.AlreadyExistsException;
import br.com.spring.books.exceptions.NotFoundException;
import br.com.spring.books.models.User;
import br.com.spring.books.models.dtos.UserDTO;
import br.com.spring.books.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> list() {
        return this.userRepository.findAll();
    }

    public User create(UserDTO data) {
        Optional<User> existingUser = userRepository.findByEmail(data.getEmail());

        if (existingUser.isPresent()) {
            throw new AlreadyExistsException("E-mail já cadastrado.");
        }

        User newUser = data.toEntity();
        return this.userRepository.save(newUser);
    }

    public User read(Long id) throws NotFoundException {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public User update(Long id, UserDTO data) throws NotFoundException {
        return this.userRepository.findById(id).map(user -> {
            if (data.getName() != null) {
                user.setName(data.getName());
            }

            if (data.getPassword() != null) {
                user.setPassword(data.getPassword());
            }

            return this.userRepository.save(user);
        }).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(Long id) throws NotFoundException {
        if (!this.userRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        this.userRepository.deleteById(id);
    }
}