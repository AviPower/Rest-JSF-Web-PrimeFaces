/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.bean;

import com.sgcv.rest.jsf.web.model.Compra;
import com.sgcv.rest.jsf.web.model.Venta;
import com.sgcv.rest.jsf.web.service.AbstractFacade;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author alvarenga
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class VentaBean extends AbstractFacade<Venta> {
    @PersistenceContext(unitName = "Rest-JSF-Web-PrimeFaces_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Resource
    private SessionContext context;

    public VentaBean() {
        super(Venta.class);
    }

    @Override
    public void create(Venta entity) {
        super.create(entity);
    }

    @TransactionAttribute(REQUIRED)
    public void edit(Integer id, Venta entity) {
        super.edit(entity);
    }

    public void remove(Integer id) {
        super.remove(super.find(id));
    }

    public Venta find(Integer id) {
        return super.find(id);
    }

    @Override
    public List<Venta> findAll() {
        return super.findAll();
    }

    public List<Venta> findRange( Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countBEAN() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
     public void cargaMasiva(List<Venta> Ventas) throws IOException {
        FileWriter fichero = null;
        PrintWriter pw = null;
        fichero = new FileWriter("/home/rodrigo/Escritorio/log/venta.txt");
        pw = new PrintWriter(fichero);
        pw.println("Registro de carga masivas");
        int i=1, errorVenta=0;
        boolean rollBackVenta= false;
            for(Venta entity : Ventas){
                
                try{
                    em.persist(entity);
                    pw.println("--> Venta "+i+" persistió con éxito...");
                }catch (Exception e) {
                    pw.println("--> Venta "+i+" error al intentar persistrir...    (Cliente:"+entity.getCliente().getNombre()+"; Total: "+entity.getTotal()+") ");
                    errorVenta++;
                    rollBackVenta=true;
                }
                i++;
            }
            if(rollBackVenta){
                pw.println("Commit no exitoso..ninguna Venta persistida a causa de errores en "+errorVenta+" Ventas...");
                pw.close();
                context.getRollbackOnly();
            }
            pw.println("Commit exitoso..100% persistido");
            pw.close();
    
     }
    
    /** Llamada a Listar del EJB **/
    public List<Venta> listar(String inicio, String cantidad, String orderBy, String orderDir) {
        return super.listar(inicio, cantidad, orderBy, orderDir, "Venta");
    }

}
