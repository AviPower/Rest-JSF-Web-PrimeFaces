/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.service;

import com.sgcv.rest.jsf.web.bean.ClienteBean;
import com.sgcv.rest.jsf.web.model.Cliente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author alvarenga
 */
@Path("cliente")
public class ClienteFacadeREST {
    @EJB
    private ClienteBean clientebean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Cliente entity) {
        clientebean.create(entity);
    }
    

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Cliente entity) {
        clientebean.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        clientebean.remove(id);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente find(@PathParam("id") Integer id) {
        return clientebean.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listar(@QueryParam("inicio") @DefaultValue("1") String inicio, 
            @QueryParam("cantidad") @DefaultValue("10") String cantidad, 
            @QueryParam("orderBy") @DefaultValue("id") String orderBy,
            @QueryParam("orderDir") @DefaultValue("ASC") String orderDir) {
        return clientebean.listar(inicio, cantidad, orderBy, orderDir);
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return clientebean.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(clientebean.countBEAN());
    }
    
    @POST
    @Path("cargaMasiva")
    @Consumes(MediaType.APPLICATION_JSON)
    //@TransactionAttribute(NOT_SUPPORTED)
    public void cargaMasiva(List<Cliente> entitys) throws IOException{

        clientebean.cargaMasiva(entitys);
    }

    
    
    
}
