/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.rest.angular.web.service;

import com.mycompany.rest.angular.web.Detallecompraventa;
import com.mycompany.rest.angular.web.bean.DetallecompraventaBean;
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
@Path("detallecompraventa")
public class DetallecompraventaFacadeREST {
    @EJB
    private DetallecompraventaBean detallecompraventabean;

    @POST
    @Consumes({ "application/json"})
    public void create(Detallecompraventa entity) {
        detallecompraventabean.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({ "application/json"})
    public void edit(@PathParam("id") Integer id, Detallecompraventa entity) {
        detallecompraventabean.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        detallecompraventabean.remove(id);
    }

    @GET
    @Path("{id}")
    @Produces({ "application/json"})
    public Detallecompraventa find(@PathParam("id") Integer id) {
        return detallecompraventabean.find(id);
    }

    @GET
    @Produces({"application/json"})
    public List<Detallecompraventa> findAll() {
        return detallecompraventabean.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({ "application/json"})
    public List<Detallecompraventa> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return detallecompraventabean.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return detallecompraventabean.countBEAN();
    }

    
}
