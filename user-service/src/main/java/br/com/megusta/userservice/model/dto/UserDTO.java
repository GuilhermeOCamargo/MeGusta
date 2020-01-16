package br.com.megusta.userservice.model.dto;

import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.validators.groups.OnCreate;
import br.com.megusta.userservice.validators.groups.OnPasswordChange;
import br.com.megusta.userservice.validators.groups.OnUpdate;
import br.com.megusta.userservice.validators.annotations.ConfirmPasswordEqualsValid;
import br.com.megusta.userservice.validators.annotations.EmailUniqueValid;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Guilherme Camargo
 * */
@ConfirmPasswordEqualsValid.List({
        @ConfirmPasswordEqualsValid(senha = "password", confirmSenha = "confirmPassword", groups = {OnPasswordChange.class, OnCreate.class})
})
@EmailUniqueValid(email = "email", id = "id", groups = OnCreate.class)
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    @NotNull(message = "Insira o nome.", groups = {OnCreate.class, OnUpdate.class})
    private String name;
    @NotNull(message = "Insira o email.", groups = {OnCreate.class})
    @Email(message = "Insira um email válido.", groups = {OnCreate.class})
    private String email;
    @NotNull(message = "Insira a senha.", groups = {OnCreate.class, OnPasswordChange.class})
    @Size(min = 8, max = 15, message = "A password deve ter entre 8 e 15 caracteres.")
    private String password;
    private String confirmPassword;

    public UserDTO() {
    }
    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
