package br.com.megusta.userservice.exceptions;
/**
 * @author Guilherme Camargo
 * */
public class NotImplementedException extends RuntimeException{
    public NotImplementedException() {
        super("Não implementado");
    }
}
