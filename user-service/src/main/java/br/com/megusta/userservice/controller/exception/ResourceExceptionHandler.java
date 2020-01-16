package br.com.megusta.userservice.controller.exception;

import br.com.megusta.userservice.exceptions.BaseException;
import br.com.megusta.userservice.model.error.StandardError;
import br.com.megusta.userservice.model.error.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @author Guilherme Camargo
 * */
@RestControllerAdvice
public class ResourceExceptionHandler {
    /**
     * @param e - Exception that must be handled
     * @return Default json error to handle {@link BaseException}
     * */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<StandardError> objectNotFound(BaseException e){
        StandardError err = new StandardError(e.getStatusCode().value(), e.getMessage(), e.getErrorTimestamp());
        return ResponseEntity.status(e.getStatusCode()).body(err);
    }
    /**
     * @param e - Exception that must be handled
     * @return Default json error to handle {@link MethodArgumentNotValidException}
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public StandardError methodArgumentNotValidException(MethodArgumentNotValidException e){
        ValidationError err = new ValidationError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação.", System.currentTimeMillis());

        for(FieldError fe : e.getBindingResult().getFieldErrors()){
            err.addError(fe.getField(), fe.getDefaultMessage());
        }

        return err;
    }
    /**
     * @param e - Exception that must be handled
     * @return Default json error to handle {@link ConstraintViolationException}
     * */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public StandardError methodArgumentNotValidException(ConstraintViolationException e){
        ValidationError err = new ValidationError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação.", System.currentTimeMillis());

        for(ConstraintViolation ce : e.getConstraintViolations()){
            String field = ce.getPropertyPath().toString();
            int initPosition = field.lastIndexOf(".");
            field = field.substring(++initPosition);
            err.addError(field, ce.getMessage());
        }

        return err;
    }
}
