/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.service;

import com.sgcv.rest.jsf.web.bean.SolicitudcompraBean;
import com.sgcv.rest.jsf.web.model.Solicitudcompra;
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
@Path("solicitudcompra")
public class SolicitudcompraFacadeREST {
    @EJB
    private SolicitudcompraBean solicitudcomprabean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Solicitudcompra entity) {
        solicitudcomprabean.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Solicitudcompra entity) {
        solicitudcomprabean.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        solicitudcomprabean.remove(solicitudcomprabean.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Solicitudcompra find(@PathParam("id") Integer id) {
        return solicitudcomprabean.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Solicitudcompra> findAll() {
        return solicitudcomprabean.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Solicitudcompra> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return solicitudcomprabean.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(solicitudcomprabean.count());
    }
    
}
