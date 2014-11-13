/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.bean;

import com.sgcv.rest.jsf.web.model.Cliente;
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

/**
 *
 * @author alvarenga
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClienteBean extends AbstractFacade<Cliente>{  

    
    
    @PersistenceContext(unitName = "Rest-JSF-Web-PrimeFaces_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Resource
    private SessionContext context;
    
    public ClienteBean() {
        super(Cliente.class);
    }

    @Override
    public void create(Cliente entity) {
        super.create(entity);
    }

    public void edit( Integer id, Cliente entity) {
        super.edit(entity);
    }

    public void remove(Integer id) {
        super.remove(super.find(id));
    }

    public Cliente find( Integer id) {
        return super.find(id);
    }

    @Override
    public List<Cliente> findAll() {
        return super.findAll();
    }

    public List<Cliente> findRange(Integer from, Integer to) {
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
    public List<Cliente> listar(String inicio, String cantidad, String orderBy, String orderDir) {
        return super.listar(inicio, cantidad, orderBy, orderDir, "Cliente");
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
     public void cargaMasiva(List<Cliente> Clientes) throws IOException {
        FileWriter fichero = null;
        PrintWriter pw = null;
        fichero = new FileWriter("/home/rodrigo/Escritorio/log/cliente.txt");
        pw = new PrintWriter(fichero);
        pw.println("Registro de carga masivas");
        int i=1, errorCliente=0;
        boolean rollBackCliente= false;
            for(Cliente entity : Clientes){
                
                try{
                    em.persist(entity);
                    pw.println("--> Cliente "+i+" persistió con éxito...");
                }catch (Exception e) {
                    pw.println("--> Cliente "+i+" error al intentar persistrir...    (Nombre:"+entity.getNombre()+"; Apellido: "+entity.getApellido()+"; CI: "+entity.getCi()+") ");
                    errorCliente++;
                    rollBackCliente=true;
                }
                i++;
            }
            if(rollBackCliente){
                pw.println("Commit no exitoso..ningun cliente persistido a causa de errores en "+errorCliente+" clientes...");
                pw.close();
                context.getRollbackOnly();
            }
            pw.println("Commit exitoso..100% persistido");
            pw.close();     
            
        
    }
    
}
     