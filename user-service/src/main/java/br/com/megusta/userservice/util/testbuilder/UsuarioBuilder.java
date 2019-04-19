package br.com.megusta.userservice.util.testbuilder;


import br.com.megusta.userservice.model.domain.User;

/**
 * @author Guilherme Camargo
 *
 * This Class creates a standard {@link User} object to tests
 * */
public class UsuarioBuilder {
    private User user;

    private UsuarioBuilder(){}

    public static UsuarioBuilder create(){
        UsuarioBuilder builder = new UsuarioBuilder();
        builder.user = new User();
        builder.user.setName("Guilherme Camargo");
        builder.user.setEmail("guilherme.ocamargo@gmail.com");
        builder.user.setPassword("1234");
        return builder;
    }

    public User get(){
        return user;
    }
}
