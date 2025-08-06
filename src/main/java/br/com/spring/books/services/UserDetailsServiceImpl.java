package br.com.spring.books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.spring.books.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        br.com.spring.books.models.User user = this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                    "Usuario com o e-mail: " + email + " não encontrado."
                ));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
