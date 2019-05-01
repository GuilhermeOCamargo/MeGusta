package br.com.megusta.userservice.model.dto;

import br.com.megusta.userservice.validators.groups.OnCreate;
import br.com.megusta.userservice.validators.groups.OnPasswordChange;
import br.com.megusta.userservice.validators.groups.OnUpdate;
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
        @ConfirmSenhaEqualsValid(senha = "password", confirmSenha = "confirmPassword", groups = {OnPasswordChange.class, OnCreate.class})
})
@EmailUniqueValid(email = "email", id = "id", groups = OnCreate.class)
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull(message = "Insira o nome.", groups = {OnCreate.class, OnUpdate.class})
    private String name;
    @NotNull(message = "Insira o email.", groups = {OnCreate.class})
    @Email(message = "Insira um email válido.", groups = {OnCreate.class})
    private String email;
    @NotNull(message = "Insira a senha.", groups = {OnCreate.class, OnPasswordChange.class})
    @Size(min = 8, max = 15, message = "A password deve ter entre 8 e 15 caracteres.")
    private String password;
    private String confirmPassword;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }

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
