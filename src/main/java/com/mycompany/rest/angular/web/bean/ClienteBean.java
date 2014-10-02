/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.rest.angular.web.bean;

import com.mycompany.rest.angular.web.Cliente;
import com.mycompany.rest.angular.web.service.AbstractFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alvarenga
 */
@Stateless
public class ClienteBean extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "com.mycompany_Rest-Angular-Web_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @PreDestroy
    public void destruct()
    {
        em.close();
    }
    
    public ClienteBean() {
        super(Cliente.class);
    }
    
    @Override
    public void create(Cliente entity) {
        super.create(entity);
    }

    public void edit(Integer id, Cliente entity) {
        super.edit(entity);
    }

    public void remove( Integer id) {
        super.remove(super.find(id));
    }

    public Cliente find(Integer id) {
        return super.find(id);
    }

    @Override
    public List<Cliente> findAll() {
        return super.findAll();
    }

    public List<Cliente> findRange( Integer from,  Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countBEAN() {
        return String.valueOf(super.count());
    }

    protected EntityManager getEntityManagerBEAN() {
        return em;
    }

    @Override
    protected EntityManager getEntityManager() {
        //throw new UnsupportedOperationException("Not supported yet. NO SOPORTADO"); 
        return em;//To change body of generated methods, choose Tools | Templates.
    }
}
