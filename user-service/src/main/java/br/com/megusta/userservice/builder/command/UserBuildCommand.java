package br.com.megusta.userservice.builder.command;

import br.com.megusta.userservice.builder.Impl.UserBuilderImpl;
import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.model.dto.UserDTO;
import br.com.megusta.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Guilherme Camargo
 * */
@Component
public class UserBuildCommand {
    @Autowired
    private UserBuilderImpl userBuilderImpl;
    @Autowired
    private UserService userService;

    private User executeCreationBuild(UserDTO dto){
        userBuilderImpl.createEntity();
        return userBuilderImpl.email(dto.getEmail()).password(dto.getPassword())
                .name(dto.getName()).build();
    }
    private User executeUpdateBuild(UserDTO dto){
        userBuilderImpl.createEntity(userService.findById(dto.getId()));
       return userBuilderImpl.name(dto.getName()).build();
    }

    public User execute(UserDTO dto){
        if(dto.getId() == null)
            return executeCreationBuild(dto);
        else
            return executeUpdateBuild(dto);
    }
}
