/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.rest.angular.web.service;

import com.mycompany.rest.angular.web.Producto;
import com.mycompany.rest.angular.web.bean.ProductoBean;
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
@Path("producto")
public class ProductoFacadeREST {

    @EJB
    private ProductoBean productobean;
    

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Producto entity) {
        productobean.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Producto entity) {
        productobean.edit(id,entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        productobean.remove(id);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Producto find(@PathParam("id") Integer id) {
        return productobean.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> findAll() {
        return productobean.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return productobean.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return productobean.countBEAN();
    }
 
}
