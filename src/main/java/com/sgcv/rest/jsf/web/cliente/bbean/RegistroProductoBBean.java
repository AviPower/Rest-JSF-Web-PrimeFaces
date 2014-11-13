/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.cliente.bbean;

import com.sgcv.rest.jsf.web.cliente.common.RestClient;
import com.sgcv.rest.jsf.web.cliente.util.GsonConverter;
import com.sgcv.rest.jsf.web.cliente.util.JsfUtils;
import com.sgcv.rest.jsf.web.model.Producto;
import com.sgcv.rest.jsf.web.model.Proveedor;
import com.sun.jersey.api.client.ClientResponse;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author alvarenga
 */
@ManagedBean
@ViewScoped
public class RegistroProductoBBean implements Serializable {
    
    /*anotation forma de inyectar en el controlador,
    ManagedProperty utilizar ese bean almacenado
    en la sesión actual de modo a pueda tener acceso
    a la totalidad de sus valores generador por RestClient.*/
    @ManagedProperty(value = "#{restClient}")
    private RestClient restClient;
    
    private String jsonHistory;
    //Create producto
    private List<Proveedor> proveedores;
    private Producto producto;
    
    public RegistroProductoBBean(){
        limpiar();
    }
    @PostConstruct
    public void inicializar() {
        limpiar();
        if(proveedores == null){
            
            ClientResponse response = restClient.clientGetResponse("proveedor");
            if(response.getStatus() != 200){
                throw new RuntimeException("Failed service call: HTTP error code : " + response.getStatus());
            }
            jsonHistory=response.getEntity(String.class);
            Proveedor[] pro = GsonConverter.getGson().fromJson(jsonHistory, Proveedor[].class);
            proveedores = Arrays.asList(pro);
        }
    }
    
    private void limpiar() {
	producto = new Producto();
    }
 

    public void guardar(ActionEvent event){
        System.out.print("HOLLLAAAAAAAA");
        String input = GsonConverter.getGson().toJson(producto);
        restClient.clientPostResponse("producto", input);
        limpiar();
        JsfUtils.addInfoMessage("Producto guardado correctamente!");
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

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
 
}
