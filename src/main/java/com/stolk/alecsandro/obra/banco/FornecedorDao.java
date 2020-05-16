package com.stolk.alecsandro.obra.banco;

import com.stolk.alecsandro.obra.modelo.Fornecedor;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class FornecedorDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;
    private Dao<Fornecedor> dao;

    @PostConstruct
    void init() {
        this.dao = new Dao<Fornecedor>(this.em, Fornecedor.class);
    }

    public Dao<Fornecedor> cadastrar(Fornecedor entidade) {
        return dao.cadastrar(entidade);
    }

    public void excluir(Fornecedor entidade) {
        dao.excluir(entidade);
    }

    public void editar(Fornecedor entidade) {
        dao.editar(entidade);
    }

    public List<Fornecedor> buscar() {
        return dao.buscar();
    }

    public Fornecedor buscar(Long id) {
        return dao.buscar(id);
    }

    public int contar() {
        return dao.contar();
    }

    public List<Fornecedor> buscar(int firstResult, int maxResults) {
        return dao.buscar(firstResult, maxResults);
    }
}
