package com.stolk.alecsandro.obra.banco;

import com.stolk.alecsandro.obra.modelo.Conta;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class ContaDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;
    private Dao<Conta> dao;

    @PostConstruct
    void init() {
        this.dao = new Dao<>(this.em, Conta.class);
    }

    public Dao<Conta> cadastrar(Conta entidade) {
        return this.dao.cadastrar(entidade);
    }

    public void excluir(Conta entidade) {
        this.dao.excluir(entidade);
    }

    public void editar(Conta entidade) {
        this.dao.editar(entidade);
    }

    public List<Conta> buscar() {
        return this.dao.buscar();
    }

    public Conta buscar(Long id) {
        return this.dao.buscar(id);
    }

    public int contar() {
        return this.dao.contar();
    }

    /*public List<Conta> buscar(int firstResult, int maxResults) {
        return this.dao.buscar(firstResult, maxResults);
    }*/
}
