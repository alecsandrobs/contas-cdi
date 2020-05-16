package com.stolk.alecsandro.obra.recurso;

import com.stolk.alecsandro.obra.banco.Dao;
import com.stolk.alecsandro.obra.modelo.Contato;
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
@Path("contatos")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class ContatoResource implements Serializable {

    private static final long serialVersionUID = 1L;

    Contato contato = new Contato();

    @Inject
    private Dao<Contato, Long> dao;

    @GET
    public Response get() {
        List<Contato> contatos = dao.buscar();
        return Response.ok(contatos).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        Contato contato = dao.buscar(id);
        return Response.ok(contato).build();
    }

    @GET
    @Path("quantidade")
    public Response getQuantidade() {
        Long quantidade = dao.buscarQuantidade();
        return Response.ok(quantidade).build();
    }

    @POST
    @Transacional
    public Response post(Contato contato) {
        dao.cadastrar(contato);
        return Response.created(URI.create(String.format("/contatos/%s", contato.getId()))).build();
    }

    @PUT
    @Transacional
    public Response put(Contato contato) {
        dao.editar(contato);
        return Response.ok(URI.create(String.format("/contatos/%s", contato.getId()))).build();
    }

    @PUT
    @Transacional
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Contato contato) {
        Contato contatoBusca = dao.buscar(id);
        contato.setId(contatoBusca.getId());
        dao.editar(contato);
        return Response.ok(URI.create(String.format("/contatos/%s", contato.getId()))).build();
    }

    @DELETE
    @Transacional
    public Response delete(Contato contato) {
        Contato contatoRemover = dao.buscar(contato.getId());
        if (contatoRemover != null) {
            dao.excluir(contatoRemover);
        }
        return Response.noContent().build();
    }

    @DELETE
    @Transacional
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Contato contato = dao.buscar(id);
        if (contato != null) {
            dao.excluir(contato);
        }
        return Response.noContent().build();
    }
}
