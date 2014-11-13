/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.bean;

import com.sgcv.rest.jsf.web.model.Factura;
import com.sgcv.rest.jsf.web.model.Venta;
import com.sgcv.rest.jsf.web.service.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;

/**
 *
 * @author alvarenga
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FacturaBean extends AbstractFacade<Factura>{
    @PersistenceContext(unitName = "Rest-JSF-Web-PrimeFaces_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @EJB
    VentaBean ventabean;
    
    private String estado = "Sin Proceso de Facturacion";
    private Transaction t= null;

    public FacturaBean() {
        super(Factura.class);
    }

    @Override
    @TransactionAttribute(REQUIRED)
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
    
    /** Llamada a Listar del EJB **/
    public List<Factura> listar(String inicio, String cantidad, String orderBy, String orderDir) {
        return super.listar(inicio, cantidad, orderBy, orderDir, "Factura");
    }
    
    /*
    Llamada al proceso Asyncrono de IniciarFacturacion
    */
    @Asynchronous
    @TransactionAttribute(REQUIRES_NEW)
    public void iniciarFacturacion() throws Exception {
        
        estado = "Corriendo";
        
        Query query = em.createQuery("SELECT v from Venta v WHERE v.factura IS NULL");
        List<Venta> listaDeVentasSinFacturas = (List<Venta>) query.getResultList();
        
        for(Venta v : listaDeVentasSinFacturas){
            Factura nuevaFactura = new Factura();
            nuevaFactura.setFecha(new Date());
            nuevaFactura.setMonto(v.getTotal());
            nuevaFactura.setVenta(v);
            create(nuevaFactura);
            v.setFactura(nuevaFactura);
            ventabean.edit(v);
            /*
            generarPDFporJAsperReports(v.getId());
            */
        }
        
        estado = "Terminado";
        
        
    }
    
    public String verEstadoFacturacion(){
        return this.estado;
    }
    
    public boolean cancelarFacturacion()throws Exception{
        if (this.estado != "Terminado"){
            em.getTransaction().rollback();
            return true;
        }else{
            return false;
        }
    }
}
