package br.com.spring.books.models.DTO;

import br.com.spring.books.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String password;

    public User toEntity() {
        User user = new User();
        user.setName(getName());
        user.setEmail(getEmail());
        user.setPassword(getPassword());
        return user;
    }
}
