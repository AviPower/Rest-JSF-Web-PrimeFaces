/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.bean;

import com.sgcv.rest.jsf.web.model.Producto;
import com.sgcv.rest.jsf.web.model.Solicitudcompra;
import com.sgcv.rest.jsf.web.service.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author alvarenga
 */
@Stateless
public class ProductoBean extends AbstractFacade<Producto> {
    @PersistenceContext(unitName = "Rest-JSF-Web-PrimeFaces_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ProductoBean() {
        super(Producto.class);
    }

    @Override
    public void create(Producto entity) {
        super.create(entity);
    }

    public void edit(Integer id, Producto entity) {
        super.edit(entity);
    }

    public void remove(Integer id) {
        super.remove(super.find(id));
    }

    public Producto find(Integer id) {
        return super.find(id);
    }

    @Override
    public List<Producto> findAll() {
        return super.findAll();
    }

    public List<Producto> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countBEAN() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /** Llamada a Listar del EJB **/
    public List<Producto> listar(String inicio, String cantidad, String orderBy, String orderDir) {
        return super.listar(inicio, cantidad, orderBy, orderDir, "Producto");
    }
    
    @EJB
    SolicitudcompraBean solicitudcomprabean;
    
    @Asynchronous
    public void controlDeInventario(){
        long tinicio = System.currentTimeMillis();
        long tfin = System.currentTimeMillis();
        while(true){
            tfin = System.currentTimeMillis();
            if (tfin - tinicio >= 180000){
                tinicio = System.currentTimeMillis();
                Query query = em.createQuery("SELECT p from Producto p where p.stock <= 10");
                List<Producto> productosConPocoStock = query.getResultList();
                for (Producto p : productosConPocoStock){
                    Solicitudcompra solicitud = new Solicitudcompra();
                    solicitud.setFecha(new Date());
                    solicitud.setHora(new Date());
                    solicitud.setProducto(p);
                }
            }
        }
    }
}
