package br.com.megusta.userservice.model.dto;

import br.com.megusta.userservice.validators.annotations.ConfirmSenhaEqualsValid;
import br.com.megusta.userservice.validators.annotations.EmailUniqueValid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Guilherme Camargo
 * */
@ConfirmSenhaEqualsValid.List({
        @ConfirmSenhaEqualsValid(senha = "password", confirmSenha = "confirmPassword")
})
@EmailUniqueValid(email = "email", id = "id")
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull(message = "Insira o nome.")
    private String name;
    @NotNull(message = "Insira o email.")
    @Email(message = "Insira um email válido.")
    private String email;
    @NotNull(message = "Insira a senha.")
    @Size(min = 8, max = 15, message = "A password deve ter entre 8 e 15 caracteres.")
    private String password;
    private String confirmPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
