package br.com.megusta.userservice.builder.Impl;

import br.com.megusta.userservice.builder.BaseBuilder;
import br.com.megusta.userservice.builder.Builder;
import br.com.megusta.userservice.model.domain.User;
import org.springframework.stereotype.Component;

/**
 * @author Guilherme Camargo
 * */
@Component
public class UserBuilderImpl extends BaseBuilder<User> {

    public UserBuilderImpl id(String id){
        entity.setId(id);
        return this;
    }
    public UserBuilderImpl name(String name){
        entity.setName(name);
        return this;
    }
    public UserBuilderImpl email(String email){
        entity.setEmail(email);
        return this;
    }
    public UserBuilderImpl password(String password){
        entity.setPassword(password);
        return this;
    }
    @Override
    public Builder createEntity() {
        entity = new User();
        return this;
    }
}
