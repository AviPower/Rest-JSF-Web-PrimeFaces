
package com.sgcv.rest.jsf.web.service;

import com.sgcv.rest.jsf.web.bean.PagoBean;
import com.sgcv.rest.jsf.web.model.Pago;
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
@Path("pago")
public class PagoFacadeREST {
    @EJB
    private PagoBean pagobean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Pago entity) {
        pagobean.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Pago entity) {
        pagobean.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        pagobean.remove(pagobean.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pago find(@PathParam("id") Integer id) {
        return pagobean.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pago> findAll() {
        return pagobean.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pago> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return pagobean.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(pagobean.count());
    }
   
}
