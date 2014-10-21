/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.bean;

import com.sgcv.rest.jsf.web.model.Factura;
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
public class FacturaBean extends AbstractFacade<Factura>{
    @PersistenceContext(unitName = "Rest-JSF-Web-PrimeFaces_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public FacturaBean() {
        super(Factura.class);
    }

    @Override
    public void create(Factura entity) {
        super.create(entity);
    }

    public void edit( Integer id, Factura entity) {
        super.edit(entity);
    }

    public void remove(Integer id) {
        super.remove(super.find(id));
    }

    public Factura find( Integer id) {
        return super.find(id);
    }

    @Override
    public List<Factura> findAll() {
        return super.findAll();
    }

    public List<Factura> findRange( Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countBEAN() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
