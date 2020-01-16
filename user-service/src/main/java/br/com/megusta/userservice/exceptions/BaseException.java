package br.com.megusta.userservice.exceptions;

import org.springframework.http.HttpStatus;


public abstract class BaseException extends RuntimeException{
    private HttpStatus statusCode;
    private long errorTimestamp;

    public BaseException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.errorTimestamp = System.currentTimeMillis();
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public long getErrorTimestamp() {
        return errorTimestamp;
    }
}