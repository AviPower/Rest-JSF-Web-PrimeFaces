/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.bean;

import com.sgcv.rest.jsf.web.model.Pago;
import com.sgcv.rest.jsf.web.service.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alvarenga
 */
@Stateless
public class PagoBean extends AbstractFacade<Pago> {
    @PersistenceContext(unitName = "Rest-JSF-Web-PrimeFaces_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PagoBean() {
        super(Pago.class);
    }

    @Override
    public void create(Pago entity) {
        super.create(entity);
    }

    public void edit( Integer id, Pago entity) {
        super.edit(entity);
    }

    public void remove(Integer id) {
        super.remove(super.find(id));
    }

    public Pago find(Integer id) {
        return super.find(id);
    }

    @Override
    public List<Pago> findAll() {
        return super.findAll();
    }

    public List<Pago> findRange( Integer from, Integer to) {
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
    public List<Pago> listar(String inicio, String cantidad, String orderBy, String orderDir) {
        return super.listar(inicio, cantidad, orderBy, orderDir, "Pago");
    }
}
