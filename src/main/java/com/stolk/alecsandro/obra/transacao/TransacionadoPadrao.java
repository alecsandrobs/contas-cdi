package com.stolk.alecsandro.obra.transacao;

import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Typed(Transacionado.class)
public class TransacionadoPadrao implements Transacionado {

    private static final long serialVersionUID = 1L;

    @Inject
    protected EntityManager em;

    public Object executaComTransacao(InvocationContext context) {
        em.getTransaction().begin();
        try {
            Object resultado = context.proceed();
            em.getTransaction().commit();
            return resultado;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }
}