package com.stolk.alecsandro.obra.banco;

import com.stolk.alecsandro.obra.modelo.Lancamento;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class LancamentoDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;
    private Dao<Lancamento> dao;

    @PostConstruct
    void init() {
        this.dao = new Dao<Lancamento>(this.em, Lancamento.class);
    }

    public Dao<Lancamento> cadastrar(Lancamento entidade) {
        return dao.cadastrar(entidade);
    }

    public void excluir(Lancamento entidade) {
        dao.excluir(entidade);
    }

    public void editar(Lancamento entidade) {
        dao.editar(entidade);
    }

    public List<Lancamento> buscar() {
        return dao.buscar();
    }

    public Lancamento buscar(Long id) {
        return dao.buscar(id);
    }

    public int contar() {
        return dao.contar();
    }

    public List<Lancamento> buscar(int firstResult, int maxResults) {
        return dao.buscar(firstResult, maxResults);
    }
}
