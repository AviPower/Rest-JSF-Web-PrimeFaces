/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.rest.angular.web.service;

import com.mycompany.rest.angular.web.Compraventa;
import com.mycompany.rest.angular.web.bean.CompraventaBean;
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
@Path("compraventa")
public class CompraventaFacadeREST {
    
    @EJB
    private CompraventaBean compraventabean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Compraventa entity) {
        compraventabean.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Compraventa entity) {
        compraventabean.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        compraventabean.remove(id);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Compraventa find(@PathParam("id") Integer id) {
        return compraventabean.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Compraventa> findAll() {
        return compraventabean.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Compraventa> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return compraventabean.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return compraventabean.countBEAN();
    }

    
}
