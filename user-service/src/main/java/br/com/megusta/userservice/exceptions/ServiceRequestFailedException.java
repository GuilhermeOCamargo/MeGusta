package br.com.megusta.userservice.exceptions;
/**
 * @author Guilherme Camargo
 * */
public class ServiceRequestFailedException extends RuntimeException{
    public ServiceRequestFailedException(String message) {
        super(message);
    }
    public ServiceRequestFailedException() {
        super();
    }
    public ServiceRequestFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
