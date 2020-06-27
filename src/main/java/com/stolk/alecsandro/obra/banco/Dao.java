package com.stolk.alecsandro.obra.banco;

import com.stolk.alecsandro.obra.modelo.Lancamento;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class Dao<E, I> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Class<E> classe;

    private EntityManager em;

    public Dao(Class<E> classe, EntityManager em) {
        this.classe = classe;
        this.em = em;
    }

    public void cadastrar(E entidade) {
        em.persist(entidade);
    }

    public void editar(E entidade) {
        em.merge(entidade);
    }

    public void excluir(E entidade) {
        em.remove(entidade);
    }

    public List<E> buscar() {
        CriteriaQuery<E> query = em.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        List<E> lista = em.createQuery(query).getResultList();
        return lista;
    }

    public E buscar(I id) {
        E instancia = em.find(classe, id);
        return instancia;
    }

    public Long buscarQuantidade() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(classe)));
        Long result = em.createQuery(query).getSingleResult();
        return result;
    }

    public Double buscarSoma(Lancamento.TipoLancamento tipo, boolean pago) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Double> query = builder.createQuery(Double.class);
        Root<E> colunas = query.from(classe);

        Expression filtraTipo = builder.equal(colunas.get("tipo"), tipo);
        Expression filtraEfetivado = builder.isNotNull(colunas.get("pagamento"));

        query.where(filtraTipo);
        if (pago) query.where(builder.and(filtraTipo, filtraEfetivado));

        Double valor = em.createQuery(query.select(builder.sum(colunas.get("valor")))).getSingleResult();
        return valor;
    }

    public List<E> buscarPaginado(int firstResult, int maxResults) {
        CriteriaQuery<E> query = em.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        List<E> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
        return lista;
    }

}