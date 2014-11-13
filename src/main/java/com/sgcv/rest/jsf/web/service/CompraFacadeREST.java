/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.service;

import com.sgcv.rest.jsf.web.bean.CompraBean;
import com.sgcv.rest.jsf.web.bean.ProveedorBean;
import com.sgcv.rest.jsf.web.model.Cliente;
import com.sgcv.rest.jsf.web.model.Compra;
import com.sgcv.rest.jsf.web.model.Proveedor;
import java.io.IOException;
import java.util.ArrayList;
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
@Path("compra")
public class CompraFacadeREST {
    @EJB
    private CompraBean comprabean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Compra entity) {
        comprabean.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Compra entity) {
        comprabean.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        comprabean.remove(comprabean.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Compra find(@PathParam("id") Integer id) {
        return comprabean.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Compra> findAll() {
        return comprabean.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Compra> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return comprabean.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(comprabean.countBEAN());
    }
    
    @EJB
    ProveedorBean proveedorBean;
    
    @POST
    @Path("cargaMasiva")
    @Consumes(MediaType.APPLICATION_JSON)
    //@TransactionAttribute(NOT_SUPPORTED)
    public void cargaMasiva(List<Compra> entitys) throws IOException{
        List<Compra> listaP = new ArrayList<Compra>();
        comprabean.cargaMasiva(entitys);
    }
    
}
