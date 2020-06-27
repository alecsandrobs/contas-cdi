package com.stolk.alecsandro.obra.banco;

import com.stolk.alecsandro.obra.modelo.Lancamento;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class LancamentoDao {

    @Inject
    @Query("select sum(l.valor) from Lancamento l where tipo = :pTipo")
    private TypedQuery<Double> query;

    public Double somar(Lancamento.TipoLancamento tipo) {
        Double valor = 0.00;
//        Criterion pagamento = pago ? Restrictions.isNotNull("pagamento") : Restrictions.isNull("pagamento");
        query.setParameter("pTipo", tipo);
        try {
            valor = query.getSingleResult();
        } catch (NoResultException ex) {
            return 0.0;
        }
        return valor;
    }
}
