/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.cliente.bbean;

import com.sgcv.rest.jsf.web.cliente.common.RestClient;
import com.sgcv.rest.jsf.web.cliente.util.GsonConverter;
import com.sgcv.rest.jsf.web.cliente.util.JsfUtils;
import com.sgcv.rest.jsf.web.model.Cliente;
import com.sgcv.rest.jsf.web.model.Compra;
import com.sgcv.rest.jsf.web.model.Compradetalle;
import com.sgcv.rest.jsf.web.model.Producto;
import com.sgcv.rest.jsf.web.model.Proveedor;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author alvarenga
 */
@ManagedBean
@ViewScoped
public class RegistroCompraBBean implements Serializable {
    
    /*anotation forma de inyectar en el controlador,
    ManagedProperty utilizar ese bean almacenado
    en la sesión actual de modo a pueda tener acceso
    a la totalidad de sus valores generador por RestClient.*/
    @ManagedProperty(value = "#{restClient}")
    private RestClient restClient;
    
    private String jsonHistory;
    //Create entidad
    private Compra compra;
    private List<Proveedor> proveedores;
    private Proveedor proveedor;
    private List<Compradetalle> compraDetalles;
    private Compradetalle compradetalle;
    private List<Producto> productos;
    
    public RegistroCompraBBean(){
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
<<<<<<< HEAD
            Proveedor[] prov = GsonConverter.getGson().fromJson(jsonHistory, Proveedor[].class);
            proveedores = Arrays.asList(prov);
=======
            Proveedor[] pro = GsonConverter.getGson().fromJson(jsonHistory, Proveedor[].class);
            proveedores = Arrays.asList(pro);
>>>>>>> f2962048fee8ba2b7c36a1e44088c99aee84061b
        }
        if(productos == null){
            
            ClientResponse response = restClient.clientGetResponse("producto");
            if(response.getStatus() != 200){
                throw new RuntimeException("Failed service call: HTTP error code : " + response.getStatus());
            }
            jsonHistory=response.getEntity(String.class);
            Producto[] pro = GsonConverter.getGson().fromJson(jsonHistory, Producto[].class);
            productos = Arrays.asList(pro);
        }
    }
    
    private void limpiar() {
	compra = new Compra();
        Compradetalle compradetalle = new Compradetalle();
        List<Compradetalle> compraDetalles = new ArrayList<Compradetalle>();
        List<Producto> productos = new ArrayList<Producto>();
<<<<<<< HEAD
        List<Proveedor> proveedores = new ArrayList<Proveedor>();
=======
>>>>>>> f2962048fee8ba2b7c36a1e44088c99aee84061b
        
    }
 

    public void guardarCompra(){
        System.out.print("HOLLLAAAAAAAA");
        String input = GsonConverter.getGson().toJson(compra);
        restClient.clientPostResponse("compra", input);
        limpiar();
        guardarDetalle();
        JsfUtils.addInfoMessage("Compra guardada correctamente!");
    }
    
    public void guardarDetalle(){
        System.out.print("HOLLLAAAAAAAA");
        String input = GsonConverter.getGson().toJson(compra);
        restClient.clientPostResponse("compra", input);
        limpiar();
        JsfUtils.addInfoMessage("DetalleCompra guardada correctamente!");
    }
    
    public void agregarDetalle(){
<<<<<<< HEAD
        compraDetalles.add(compradetalle);
        Compradetalle compradetalle = new Compradetalle();
=======
    
>>>>>>> f2962048fee8ba2b7c36a1e44088c99aee84061b
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

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public List<Compradetalle> getCompraDetalles() {
        return compraDetalles;
    }

    public Compradetalle getCompradetalle() {
        return compradetalle;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void setCompraDetalles(List<Compradetalle> compraDetalles) {
        this.compraDetalles = compraDetalles;
    }

    public void setCompradetalle(Compradetalle compradetalle) {
        this.compradetalle = compradetalle;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }
<<<<<<< HEAD

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }
    
=======
    
 
>>>>>>> f2962048fee8ba2b7c36a1e44088c99aee84061b
}
