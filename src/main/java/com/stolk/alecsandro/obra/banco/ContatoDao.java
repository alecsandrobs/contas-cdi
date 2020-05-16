package com.stolk.alecsandro.obra.banco;

import com.stolk.alecsandro.obra.modelo.Contato;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class ContatoDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;
    private Dao<Contato> dao;

    @PostConstruct
    void init() {
        this.dao = new Dao<Contato>(this.em, Contato.class);
    }

    public Dao<Contato> cadastrar(Contato entidade) {
        return dao.cadastrar(entidade);
    }

    public void excluir(Contato entidade) {
        dao.excluir(entidade);
    }

    public void editar(Contato entidade) {
        dao.editar(entidade);
    }

    public List<Contato> buscar() {
        return dao.buscar();
    }

    public Contato buscar(Long id) {
        return dao.buscar(id);
    }

    public int contar() {
        return dao.contar();
    }

    public List<Contato> buscar(int firstResult, int maxResults) {
        return dao.buscar(firstResult, maxResults);
    }
}
