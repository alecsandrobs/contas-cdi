package com.stolk.alecsandro.obra.banco;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class Dao<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Class<E> classe;
    private EntityManager em;

    public Dao(EntityManager em, Class<E> classe) {
        this.em = em;
        this.classe = classe;
    }

    public Dao<E> cadastrar(E entidade) {
        em.persist(entidade);
        return this;
    }

    public void excluir(E entidade) {
        em.remove(em.merge(entidade));
    }

    public void editar(E entidade) {
        em.merge(entidade);
    }

    public List<E> buscar() {
        if (classe == null) {
            throw new UnsupportedOperationException("A classe está nula.");
        }
        CriteriaQuery<E> query = em.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        List<E> lista = em.createQuery(query).getResultList();
        return lista;
    }

    public E buscar(Long id) {
        E instancia = em.find(classe, id);
        return instancia;
    }

    public int contar() {
        if (classe == null) {
            throw new UnsupportedOperationException("A classe está nula.");
        }
        String jpql = String.format("select e from %s e", classe.getName());
        long result = (Long) em.createQuery(jpql)
                .getSingleResult();
        return (int) result;
    }

    public List<E> buscar(int firstResult, int maxResults) {
        CriteriaQuery<E> query = em.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        List<E> lista = em.createQuery(query).setFirstResult(firstResult)
                .setMaxResults(maxResults).getResultList();
        return lista;
    }
}
