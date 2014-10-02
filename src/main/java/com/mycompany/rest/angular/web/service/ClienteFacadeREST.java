/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.rest.angular.web.service;

import com.mycompany.rest.angular.web.Cliente;
import com.mycompany.rest.angular.web.bean.ClienteBean;
import java.util.List;
import javax.ejb.ApplicationException;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rodrigo
 */

@Path("cliente")
public class ClienteFacadeREST  {
    
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
        clientebean.edit(id, entity);
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
    public List<Cliente> findAll() {
        return clientebean.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return clientebean.findRange(from, to);
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return clientebean.countBEAN();
    }

}
