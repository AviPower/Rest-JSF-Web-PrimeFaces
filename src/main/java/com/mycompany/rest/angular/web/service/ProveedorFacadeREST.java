/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.rest.angular.web.service;

import com.mycompany.rest.angular.web.Proveedor;
import com.mycompany.rest.angular.web.bean.ProveedorBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Stateless
@Path("proveedor")
public class ProveedorFacadeREST{
    
    @EJB
    private ProveedorBean proveedorbean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Proveedor entity) {
        proveedorbean.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Proveedor entity) {
        proveedorbean.edit(id, entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        proveedorbean.remove(id);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Proveedor find(@PathParam("id") Integer id) {
        return proveedorbean.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proveedor> findAll() {
        return proveedorbean.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proveedor> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return proveedorbean.findRange(from, to);
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return proveedorbean.countBEAN();
    }
    
}
