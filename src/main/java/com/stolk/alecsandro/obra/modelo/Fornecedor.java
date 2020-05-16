package com.stolk.alecsandro.obra.modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "fornecedores")
public class Fornecedor extends EntidadeId {

    @NotBlank(message = "O nome deve ser infromado")
    private String nome;

    private String documento;

    @ManyToMany(fetch = LAZY)
    private List<Conta> contas;

    @ManyToMany(fetch = LAZY)
    private List<Contato> contatos;

    public Fornecedor() {
    }

    public Fornecedor(@NotBlank(message = "O nome deve ser infromado") String nome, String documento, List<Conta> contas, List<Contato> contatos) {
        this.nome = nome;
        this.documento = documento;
        this.contas = contas;
        this.contatos = contatos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public Fornecedor adicionarConta(Conta conta) {
        contas.add(conta);
        return this;
    }

    public Fornecedor removerConta(Long id) {
        contas.removeIf(conta -> id.equals(conta.getId()));
        return this;
    }

    public void substituirConta(Long id, Conta conta) {
        removerConta(id);
        adicionarConta(conta);
    }

    public Fornecedor adicionarContato(Contato contato) {
        contatos.add(contato);
        return this;
    }

    public Fornecedor removerContato(Long id) {
        contatos.removeIf(contato -> id.equals(contato.getId()));
        return this;
    }

    public void substituirContato(Long id, Contato contato) {
        removerContato(id);
        adicionarContato(contato);
    }
}
