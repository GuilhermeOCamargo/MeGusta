package br.com.megusta.userservice.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Guilherme Camargo
 * */
public class DataIntegrityException extends BaseException {

    public DataIntegrityException(String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
