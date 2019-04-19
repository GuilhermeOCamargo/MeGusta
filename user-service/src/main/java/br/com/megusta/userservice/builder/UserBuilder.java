package br.com.megusta.userservice.builder;

import br.com.megusta.userservice.model.domain.Address;
import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.model.dto.UserDTO;

/**
 * @author Guilherme Camargo
 * */
public class UserBuilder {
    private User user;

    public UserBuilder(){
        this.user = new User();
    }
    public UserBuilder(User user){
        this.user = user;
    }
    public UserBuilder id(Long id){
        this.user.setId(id);
        return this;
    }
    public UserBuilder name(String name){
        this.user.setName(name);
        return this;
    }
    public UserBuilder email(String email){
        this.user.setEmail(email);
        return this;
    }
    public UserBuilder password(String password){
        this.user.setPassword(password);
        return this;
    }
    public UserBuilder address(Address address){
        this.user.setAddress(address);
        return this;
    }
    public User build(){
        return this.user;
    }
    public UserBuilder createFromDto(UserDTO dto){
        populateBasicValues(dto);
        email(dto.getEmail());
        password(dto.getPassword());
        return this;
    }
    public UserBuilder updateObject(UserDTO dto){
        populateBasicValues(dto);
        return this;
    }

    private void populateBasicValues(UserDTO dto){
        name(dto.getName());
    }
}
