package br.com.megusta.userservice.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Guilherme Camargo
 * */
@Document(collection = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @DBRef(lazy = true)
    private List<Address> addresses = new ArrayList<>();

    private User(String id, String name, String email, String password, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.addresses = addresses;
    }
    @Deprecated
    public User() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, addresses);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address=" + addresses +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public UserBuilder builder(){
        return new UserBuilder();
    }

    private static class UserBuilder{

        private String id;
        private String name;
        private String email;
        private String password;
        private List<Address> addresses;

        public UserBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withAddresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public User createUser() {
            return new User(id, name, email, password, addresses);
        }
    }
}