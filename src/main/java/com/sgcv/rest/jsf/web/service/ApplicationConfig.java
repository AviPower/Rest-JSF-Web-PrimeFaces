/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.service;

import java.util.Set;
import javax.ws.rs.core.Application;
/**
 *
 * @author alvarenga
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.sgcv.rest.jsf.web.service.ClienteFacadeREST.class);
        resources.add(com.sgcv.rest.jsf.web.service.CompraFacadeREST.class);
        resources.add(com.sgcv.rest.jsf.web.service.CompradetalleFacadeREST.class);
        resources.add(com.sgcv.rest.jsf.web.service.DeudaFacadeREST.class);
        resources.add(com.sgcv.rest.jsf.web.service.FacturaFacadeREST.class);
        resources.add(com.sgcv.rest.jsf.web.service.PagoFacadeREST.class);
        resources.add(com.sgcv.rest.jsf.web.service.ProductoFacadeREST.class);
        resources.add(com.sgcv.rest.jsf.web.service.ProveedorFacadeREST.class);
        resources.add(com.sgcv.rest.jsf.web.service.SolicitudcompraFacadeREST.class);
        resources.add(com.sgcv.rest.jsf.web.service.VentaFacadeREST.class);
        resources.add(com.sgcv.rest.jsf.web.service.VentadetalleFacadeREST.class);
    }
}
