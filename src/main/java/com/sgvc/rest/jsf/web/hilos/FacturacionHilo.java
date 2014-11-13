/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgvc.rest.jsf.web.hilos;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author Miguel León
 */

@TransactionManagement(TransactionManagementType.CONTAINER)
public class FacturacionHilo extends Thread{
    
    private boolean continuarFacturacion = false;

    public FacturacionHilo() {
        super();
    }
    
    
}
