package com.stolk.alecsandro.obra.transacao;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import java.io.Serializable;

@Interceptor
@Transacional
public class GerenciadorTransacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    EntityManager em;

    @AroundInvoke
    public Object executaTransacao(InvocationContext contexto) throws Exception {
        em.getTransaction().begin();
        Object resultado = contexto.proceed();
        em.getTransaction().commit();
        return resultado;
    }
}
