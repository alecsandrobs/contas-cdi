package com.stolk.alecsandro.obra.transacao;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;


@Interceptor
@Transacional
public class GerenciadorTransacao implements Serializable {

    private static final long serialVersionUID = 1L;
    private Transacionado transacionado;

    @Inject
    public GerenciadorTransacao(Transacionado transacionado) {
        this.transacionado = transacionado;
    }

    @AroundInvoke
    public Object interceptar(InvocationContext context) {
        return transacionado.executaComTransacao(context);
    }

}