package br.com.megusta.userservice.builders;


import br.com.megusta.userservice.model.domain.User;

/**
 * @author Guilherme Camargo
 *
 * This Class creates a standard {@link User} object to tests
 * */
public class UserBuilder {
    private static User user;
    private static UserBuilder builder;

    private UserBuilder(){}

    public static UserBuilder createDefault(){
        builder = new UserBuilder();
        builder.user = new User();
        return withName("Guilherme Camargo")
        .withEmail("guilherme.ocamargo@megusta.com").withPassword("1234");
    }
    public static UserBuilder withName(String name){
        builder.user.setName(name);
        return builder;
    }
    public static UserBuilder withEmail(String email){
        builder.user.setEmail(email);
        return builder;
    }
    public static UserBuilder withPassword(String password){
        builder.user.setPassword(password);
        return builder;
    }
    public static UserBuilder withId(String id){
        builder.user.setId(id);
        return builder;
    }
    public User build(){
        return user;
    }
}
