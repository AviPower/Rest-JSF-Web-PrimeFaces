/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.service;

import com.sgcv.rest.jsf.web.bean.VentadetalleBean;
import com.sgcv.rest.jsf.web.model.Ventadetalle;
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
 * @author alvarenga
 */
@Path("ventadetalle")
public class VentadetalleFacadeREST {
    @EJB
    private VentadetalleBean ventadetallebean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Ventadetalle entity) {
        ventadetallebean.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Ventadetalle entity) {
        ventadetallebean.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        ventadetallebean.remove(ventadetallebean.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ventadetalle find(@PathParam("id") Integer id) {
        return ventadetallebean.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ventadetalle> findAll() {
        return ventadetallebean.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ventadetalle> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return ventadetallebean.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(ventadetallebean.count());
    }
    
}
