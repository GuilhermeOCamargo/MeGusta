package br.com.megusta.userservice.builder;

import br.com.megusta.userservice.exceptions.NotImplementedException;

/**
 * @author Guilherme Camargo
 * */
public abstract class BaseBuilder<T> implements Builder<T> {
    protected T entity;

    @Override
    public Builder createEntity() {
        throw new NotImplementedException();
    }

    @Override
    public Builder createEntity(T t) {
        entity = t;
        return this;
    }

    @Override
    public T build() {
        return entity;
    }
}
