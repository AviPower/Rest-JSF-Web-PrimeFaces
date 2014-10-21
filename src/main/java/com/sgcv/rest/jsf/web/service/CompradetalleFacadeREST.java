/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.service;

import com.sgcv.rest.jsf.web.bean.CompradetalleBean;
import com.sgcv.rest.jsf.web.model.Compradetalle;
import java.util.List;
import javax.ejb.EJB;
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
@Path("compradetalle")
public class CompradetalleFacadeREST {
    @EJB
    private CompradetalleBean compradetallebean;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Compradetalle entity) {
        compradetallebean.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Compradetalle entity) {
        compradetallebean.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        compradetallebean.remove(compradetallebean.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Compradetalle find(@PathParam("id") Integer id) {
        return compradetallebean.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Compradetalle> findAll() {
        return compradetallebean.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Compradetalle> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return compradetallebean.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(compradetallebean.countBEAN());
    }
    
}
