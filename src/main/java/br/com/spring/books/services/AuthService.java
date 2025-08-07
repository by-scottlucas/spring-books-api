package br.com.spring.books.services;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.spring.books.models.User;
import br.com.spring.books.models.dtos.UserDTO;
import br.com.spring.books.repositories.UserRepository;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(
            JwtService jwtService,
            UserService userService,
            BCryptPasswordEncoder passwordEncoder,
            UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(UserDTO data) {
        return userService.create(data);
    }

    public String login(String email, String password) {
        Optional<User> userOptional = this.userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtService.generateToken(user.getEmail());
                return token;
            } else {
                throw new BadCredentialsException("Senha incorreta");
            }
        } else {
            throw new BadCredentialsException("Usuário não encontrado");
        }
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadCredentialsException("Usuário não autenticado");
        }

        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Usuário não encontrado"));
    }

}
