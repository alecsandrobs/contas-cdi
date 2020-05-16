package com.stolk.alecsandro.obra.recurso;

import com.stolk.alecsandro.obra.banco.ContaDao;
import com.stolk.alecsandro.obra.modelo.Conta;
import com.stolk.alecsandro.obra.transacao.Transacional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Named
@Path("contas")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class ContaResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private Conta conta = new Conta();

    @Inject
    private ContaDao dao;

    @GET
    public Response get() {
        List<Conta> contas = this.dao.buscar();
        return Response.ok(contas).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        this.conta = this.dao.buscar(id);
        return Response.ok(this.conta).build();
    }

    @POST
    @Transacional
    public Response post(Conta conta) {
        this.dao.cadastrar(conta);
        return Response.created(URI.create(String.format("/contas/%s", conta.getId()))).build();
    }

    @PUT
    @Transacional
    public Response put(Conta conta) {
        this.dao.editar(conta);
        return Response.ok(URI.create(String.format("/contas/%s", conta.getId()))).build();
    }

    @PUT
    @Transacional
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Conta conta) {
        Conta contaBusca = this.dao.buscar(id);
        conta.setId(contaBusca.getId());
        this.dao.editar(conta);
        return Response.ok(URI.create(String.format("/contas/%s", conta.getId()))).build();
    }

    @DELETE
    @Transacional
    public Response delete(Conta conta) {
        Conta contaRemover = this.dao.buscar(conta.getId());
        if (contaRemover != null) {
            this.dao.excluir(contaRemover);
        }
        return Response.noContent().build();
    }

    @DELETE
    @Transacional
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Conta conta = this.dao.buscar(id);
        if (conta != null) {
            this.dao.excluir(conta);
        }
        return Response.noContent().build();
    }
}
