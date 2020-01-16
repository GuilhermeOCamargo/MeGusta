package br.com.megusta.userservice.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Guilherme Camargo
 * */
public class AuthenticationFailedException extends BaseException {
    public AuthenticationFailedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
