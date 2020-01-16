package br.com.megusta.userservice.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Guilherme Camargo
 * */
public class ObjectNotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
