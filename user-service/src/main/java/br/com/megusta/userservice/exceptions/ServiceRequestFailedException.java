package br.com.megusta.userservice.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Guilherme Camargo
 * */
public class ServiceRequestFailedException extends BaseException{
    public ServiceRequestFailedException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
