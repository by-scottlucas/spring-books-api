package br.com.spring.books.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.spring.books.exceptions.AlreadyExistsException;
import br.com.spring.books.exceptions.NotFoundException;
import br.com.spring.books.models.User;
import br.com.spring.books.models.dtos.UserDTO;
import br.com.spring.books.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        String encryptedPassword = passwordEncoder.encode(data.getPassword());
        newUser.setPassword(encryptedPassword);

        return this.userRepository.save(newUser);
    }

    public User read(Long id) throws NotFoundException {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado."));
    }

    public User update(Long id, UserDTO data) throws NotFoundException {
        return this.userRepository.findById(id).map(user -> {
            if (data.getName() != null) {
                user.setName(data.getName());
            }

            if (data.getPassword() != null) {
                String encryptedPassword = passwordEncoder.encode(data.getPassword());
                user.setPassword(encryptedPassword);
            }

            return this.userRepository.save(user);
        }).orElseThrow(() -> new NotFoundException("Usuario não encontrado."));
    }

    public void delete(Long id) throws NotFoundException {
        if (!this.userRepository.existsById(id)) {
            throw new NotFoundException("Usuario não encontrado.");
        }
        this.userRepository.deleteById(id);
    }
}