package br.com.spring.books.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.books.models.User;
import br.com.spring.books.models.dtos.UserDTO;
import br.com.spring.books.services.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User signUp(@Valid @RequestBody UserDTO user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> signIn(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        String token = this.authService.login(email, password);
        return Map.of("acessToken", token);
    }
}
