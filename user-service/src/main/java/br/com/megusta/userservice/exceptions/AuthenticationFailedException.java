package br.com.megusta.userservice.exceptions;
/**
 * @author Guilherme Camargo
 * */
public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException(String s) {
        super(s);
    }
}
