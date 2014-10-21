/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.service;

import com.sgcv.rest.jsf.web.bean.FacturaBean;
import com.sgcv.rest.jsf.web.model.Factura;
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
@Path("factura")
public class FacturaFacadeREST {
    @EJB
    private FacturaBean facturabean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Factura entity) {
        facturabean.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Factura entity) {
        facturabean.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        facturabean.remove(facturabean.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Factura find(@PathParam("id") Integer id) {
        return facturabean.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Factura> findAll() {
        return facturabean.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Factura> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return facturabean.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(facturabean.countBEAN());
    }
    
}
