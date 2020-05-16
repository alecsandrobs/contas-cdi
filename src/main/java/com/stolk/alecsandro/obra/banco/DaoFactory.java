package com.stolk.alecsandro.obra.banco;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

@SuppressWarnings("unchecked")
public class DaoFactory {

    @Inject
    private EntityManager manger;

    @Produces
    public <T, I> Dao<T, I> factory(InjectionPoint point) {
        ParameterizedType type = (ParameterizedType) point.getType();
        Class<T> classe = (Class<T>) type.getActualTypeArguments()[0];
        return new Dao<T, I>(classe, manger);
    }

}
