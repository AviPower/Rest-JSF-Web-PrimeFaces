/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.service;

import com.sgcv.rest.jsf.web.bean.ProductoBean;
import com.sgcv.rest.jsf.web.model.Producto;
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
        productobean.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        productobean.remove(productobean.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Producto find(@PathParam("id") Integer id) {
        return productobean.find(id);
    }

     /** Servicio de Listar Clientes **/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> listar(@QueryParam("inicio") @DefaultValue("0") String inicio, 
            @QueryParam("cantidad") @DefaultValue("10") String cantidad, 
            @QueryParam("orderBy") @DefaultValue("id") String orderBy,
            @QueryParam("orderDir") @DefaultValue("ASC") String orderDir) {
        return productobean.listar(inicio, cantidad, orderBy, orderDir);
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
        return String.valueOf(productobean.countBEAN());
    }
    
}
