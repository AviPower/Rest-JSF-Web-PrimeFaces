/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.bean;


import com.sgcv.rest.jsf.web.model.Compra;
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
public class CompraBean extends AbstractFacade<Compra> {
    @PersistenceContext(unitName = "Rest-JSF-Web-PrimeFaces_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Resource
    private SessionContext context;
    
    public CompraBean() {
        super(Compra.class);
    }

    @Override
    public void create(Compra entity) {
        super.create(entity);
    }

    public void edit( Integer id, Compra entity) {
        super.edit(entity);
    }

 
    public void remove( Integer id) {
        super.remove(super.find(id));
    }


    public Compra find( Integer id) {
        return super.find(id);
    }

    @Override
    public List<Compra> findAll() {
        return super.findAll();
    }

    public List<Compra> findRange( Integer from, Integer to) {
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
     public void cargaMasiva(List<Compra> Compras) throws IOException {
        FileWriter fichero = null;
        PrintWriter pw = null;
        fichero = new FileWriter("/home/rodrigo/Escritorio/log/compra.txt");
        pw = new PrintWriter(fichero);
        pw.println("Registro de carga masivas");
        int i=1, errorCompra=0;
        boolean rollBackCompra= false;
            for(Compra entity : Compras){
                
                try{
                    em.persist(entity);
                    pw.println("--> Compra "+i+" persistió con éxito...");
                }catch (Exception e) {
                    pw.println("--> Compra "+i+" error al intentar persistrir...    (Proveedor:"+entity.getProveedor().getNombre()+"; Monto: "+entity.getTotal()+") ");
                    errorCompra++;
                    rollBackCompra=true;
                }
                i++;
            }
            if(rollBackCompra){
                pw.println("Commit no exitoso..ninguna Compra persistida a causa de errores en "+errorCompra+" Compras...");
                pw.close();
                context.getRollbackOnly();
            }
            pw.println("Commit exitoso..100% persistido");
            pw.close();
    
     }
    
    /** Llamada a Listar del EJB **/
    public List<Compra> listar(String inicio, String cantidad, String orderBy, String orderDir) {
        return super.listar(inicio, cantidad, orderBy, orderDir, "Compra");
    }
}
