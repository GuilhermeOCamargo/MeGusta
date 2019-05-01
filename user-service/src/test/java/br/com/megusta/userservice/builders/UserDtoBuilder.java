package br.com.megusta.userservice.builders;

import br.com.megusta.userservice.model.dto.UserDTO;

/**
 * @author Guilherme Camargo
 * */
public class UserDtoBuilder {
    private static UserDTO user;
    private static UserDtoBuilder builder;

    private UserDtoBuilder(){}

    public static UserDtoBuilder createDtoDefault(){
        builder = new UserDtoBuilder();
        builder.user = new UserDTO();
        return withName("Guilherme Camargo")
                .withEmail("guilherme.camargo@megusta.com.br").withPassword("12345678");
    }
    public static UserDtoBuilder createDtoWithoutName(){
        builder = new UserDtoBuilder();
        builder.user = new UserDTO();
        return withEmail("guilherme.ocamargo@megusta.com").withPassword("12345678");
    }
    private static UserDtoBuilder withName(String name){
        builder.user.setName(name);
        return builder;
    }
    private static UserDtoBuilder withEmail(String email){
        builder.user.setEmail(email);
        return builder;
    }
    private static UserDtoBuilder withPassword(String password){
        builder.user.setPassword(password);
        builder.user.setConfirmPassword(password);
        return builder;
    }
    public UserDTO build(){
        return user;
    }
}
