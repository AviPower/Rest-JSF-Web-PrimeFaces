/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.cliente.bbean;

import com.sgcv.rest.jsf.web.cliente.common.RestClient;
import com.sgcv.rest.jsf.web.cliente.util.GsonConverter;
import com.sgcv.rest.jsf.web.cliente.util.JsfUtils;
import com.sgcv.rest.jsf.web.model.Proveedor;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author alvarenga
 */
@ManagedBean
@ViewScoped
public class RegistroProveedorBBean implements Serializable {
    
    /*anotation forma de inyectar en el controlador,
    ManagedProperty utilizar ese bean almacenado
    en la sesión actual de modo a pueda tener acceso
    a la totalidad de sus valores generador por RestClient.*/
    @ManagedProperty(value = "#{restClient}")
    private RestClient restClient;
    
    private String jsonHistory;
    //Create entidad
    private Proveedor proveedor;
    
    public RegistroProveedorBBean(){
        limpiar();
    }
    @PostConstruct
    public void inicializar() {
        limpiar();
    }
    
    private void limpiar() {
	proveedor = new Proveedor();
    }
 

    public void guardar(){
        System.out.print("HOLLLAAAAAAAA");
        String input = GsonConverter.getGson().toJson(proveedor);
        restClient.clientPostResponse("proveedor", input);
        limpiar();
        JsfUtils.addInfoMessage("Proveedor guardado correctamente!");
    }
 
    // getter / setter
    
    public RestClient getRestClient() {
        return restClient;
    }

    public void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }
    
    public String getJsonHistory() {
        return jsonHistory;
    }


    public void setJsonHistory(String jsonHistory) {
        this.jsonHistory = jsonHistory;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
 
}
