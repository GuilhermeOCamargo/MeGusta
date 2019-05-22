package br.com.megusta.userservice.builder;
/**
 * @author Guilherme Camargo
 * */
public interface Builder<T> {

    Builder createEntity();
    Builder createEntity(T t);
    T build();
}
