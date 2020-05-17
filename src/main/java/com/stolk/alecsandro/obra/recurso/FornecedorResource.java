package com.stolk.alecsandro.obra.recurso;

import com.stolk.alecsandro.obra.banco.Dao;
import com.stolk.alecsandro.obra.modelo.Conta;
import com.stolk.alecsandro.obra.modelo.Contato;
import com.stolk.alecsandro.obra.modelo.Fornecedor;
import com.stolk.alecsandro.obra.transacao.Transacional;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Model
@Path("fornecedores")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class FornecedorResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private Fornecedor fornecedor = new Fornecedor();

    @Inject
    private Dao<Fornecedor, Long> dao;

    @GET
    public Response get() {
        List<Fornecedor> fornecedores = dao.buscar();
        return Response.ok(fornecedores).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        this.fornecedor = dao.buscar(id);
        return Response.ok(this.fornecedor).build();
    }

    @GET
    @Path("quantidade")
    public Response getQuantidade() {
        Long quantidade = dao.buscarQuantidade();
        return Response.ok(quantidade).build();
    }

    @POST
    @Transacional
    public Response post(Fornecedor fornecedor) {
        dao.cadastrar(fornecedor);
        return Response.created(URI.create(String.format("/fornecedores/%s", fornecedor.getId()))).build();
    }

    @PUT
    @Transacional
    public Response put(Fornecedor fornecedor) {
        dao.editar(fornecedor);
        return Response.ok(URI.create(String.format("/fornecedores/%s", fornecedor.getId()))).build();
    }

    @PUT
    @Transacional
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Fornecedor fornecedor) {
        this.fornecedor = dao.buscar(id);
        fornecedor.setId(this.fornecedor.getId());
        dao.editar(fornecedor);
        return Response.ok(URI.create(String.format("/fornecedores/%s", fornecedor.getId()))).build();
    }

    @DELETE
    @Transacional
    public Response delete(Fornecedor fornecedor) {
        this.fornecedor = dao.buscar(fornecedor.getId());
        if (this.fornecedor != null) {
            dao.excluir(this.fornecedor);
        }
        return Response.noContent().build();
    }

    @DELETE
    @Transacional
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        this.fornecedor = dao.buscar(id);
        if (this.fornecedor != null) {
            dao.excluir(this.fornecedor);
        }
        return Response.noContent().build();
    }

    @POST
    @Transacional
    @Path("{fornecedorId}/contas")
    public Response postContas(@PathParam("fornecedorId") Long fornecedorId, Conta conta) {
        this.fornecedor = dao.buscar(fornecedorId);
        this.fornecedor.adicionarConta(conta);
        return Response.created(URI.create(String.format("/fornecedores/%s/contas/%s", fornecedorId, conta.getId()))).build();
    }

    @DELETE
    @Transacional
    @Path("{fornecedorId}/contas/{id}")
    public Response deleteContas(@PathParam("fornecedorId") Long fornecedorId, @PathParam("id") Long id) {
        this.fornecedor = dao.buscar(fornecedorId);
        this.fornecedor.removerConta(id);
        return Response.noContent().build();
    }

    @POST
    @Transacional
    @Path("{fornecedorId}/contatos")
    public Response postContatos(@PathParam("fornecedorId") Long fornecedorId, Contato contato) {
        this.fornecedor = dao.buscar(fornecedorId);
        this.fornecedor.adicionarContato(contato);
        return Response.created(URI.create(String.format("/fornecedores/%s/contatos/%s", fornecedorId, contato.getId()))).build();
    }

    @DELETE
    @Transacional
    @Path("{fornecedorId}/contatos/{id}")
    public Response deleteContatos(@PathParam("fornecedorId") Long fornecedorId, @PathParam("id") Long id) {
        this.fornecedor = dao.buscar(fornecedorId);
        this.fornecedor.removerContato(id);
        return Response.noContent().build();
    }
}
