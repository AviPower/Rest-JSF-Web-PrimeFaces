/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.service;

import com.sgcv.rest.jsf.web.bean.VentaBean;
import com.sgcv.rest.jsf.web.model.Venta;
import java.util.List;
import javax.ejb.EJB;
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
@Path("venta")
public class VentaFacadeREST{
    @EJB
    private VentaBean ventabean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Venta entity) {
        ventabean.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Venta entity) {
        ventabean.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        ventabean.remove(ventabean.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Venta find(@PathParam("id") Integer id) {
        return ventabean.find(id);
    }

     /** Servicio de Listar Clientes **/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Venta> listar(@QueryParam("inicio") @DefaultValue("0") String inicio, 
            @QueryParam("cantidad") @DefaultValue("10") String cantidad, 
            @QueryParam("orderBy") @DefaultValue("id") String orderBy,
            @QueryParam("orderDir") @DefaultValue("ASC") String orderDir) {
        return ventabean.listar(inicio, cantidad, orderBy, orderDir);
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Venta> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return ventabean.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(ventabean.countBEAN());
    }
    
}
