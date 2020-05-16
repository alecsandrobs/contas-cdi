package com.stolk.alecsandro.obra.transacao;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Log
@Interceptor
public class TempoDeExecucaoInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @AroundInvoke
    public Object log(InvocationContext contexto) throws Exception {
        long inicio = System.currentTimeMillis();
        String metodo = contexto.getMethod().getName();
        Object resultado = contexto.proceed();
        long fim = System.currentTimeMillis();
        long tempo = fim - inicio;
        System.out.println(String.format("Tempo de execução do método (%s): %s", metodo, tempo));
        return resultado;
    }
}
