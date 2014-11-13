/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.cliente.bbean;

import com.sgcv.rest.jsf.web.cliente.common.RestClient;
import com.sgcv.rest.jsf.web.cliente.util.GsonConverter;
import com.sgcv.rest.jsf.web.cliente.util.JsfUtils;
import com.sgcv.rest.jsf.web.model.Compra;
import com.sgcv.rest.jsf.web.model.Proveedor;
import com.sun.jersey.api.client.ClientResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;

/**
 *
 * @author alvarenga
 */
/**http://ovaraksin.blogspot.com/2012/12/get-post-with-restful-client-api.html
 * Bean getting history of the last extracted documents. 
  * Tenemos la intención de recibir los recursos en formato JSON y convertirlos en objetos Java. 
  * El próximo bean muestra cómo hacer esta tarea para peticiones GET. 
  * Los conversos bean ClienteBBean reciben JSON en un objeto Ciemte mediante el uso de GsonConverter. 
  * Producto es un simple POJO y GsonConverter es una instancia singleton que envuelve Gson.
 */
//Conseguir la historia de los ultimos elementos extraidos
@ManagedBean
@ViewScoped
public class CompraBBean implements Serializable {
    
    /*anotation forma de inyectar en el controlador,
    ManagedProperty utilizar ese bean almacenado
    en la sesión actual de modo a pueda tener acceso
    a la totalidad de sus valores generador por RestClient.*/
    @ManagedProperty(value = "#{restClient}")
    private RestClient restClient;
    
    //Objeto pojo
    private List<Compra> documents;
    private String jsonHistory;
    //Create producto
    private List<Proveedor> proveedores;
    private Proveedor proveedor;
    private Compra compra;
    
    public CompraBBean(){
        limpiar();
    }
    @PostConstruct
    public void inicializar() {
        limpiar();
    }
    
    private void limpiar() {
	compra = new Compra();
    }
 
    public List<Compra> getDocuments() throws Exception {
        if (documents != null) {
            return documents;
        }
        /*//Envia el path de peticion get de producto, listado de producto
        ClientResponse response = restClient.clientGetResponse("producto");
 
        //verifica que no haya error con la pagina.. si es 200 caso de exito, sino fallo
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed service call: HTTP error code : " + response.getStatus());
        }*/
        try {
        ClientRequest request = new ClientRequest(
					"http://localhost:8080/Rest-JSF-Web-PrimeFaces/webresources/compra");
        request.accept("application/json");
        org.jboss.resteasy.client.ClientResponse<String> response = request.get(String.class);

        if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                                + response.getStatus());
        }
        
        // get producto as JSON, el json se guarda como string
        jsonHistory = response.getEntity(String.class);
 
        // convert to Java array / list of Cliente instances, Convierte el json a una Lista de Productos
        //fromJson pasa de la notacion json a java, Si fuera en caso contrario se usaria toJson
        Compra[] docs = GsonConverter.getGson().fromJson(jsonHistory, Compra[].class);
        documents = Arrays.asList(docs);
 
        return documents;
        } catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}
        return null;
    }
    
    public void guardar(Compra compra){
        String input = GsonConverter.getGson().toJson(compra);
        restClient.clientPostResponse("compra", input);
        limpiar();
        JsfUtils.addInfoMessage("compra guardado correctamente!");
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

    public void setDocuments(List<Compra> documents) {
        this.documents = documents;
    }

    public void setJsonHistory(String jsonHistory) {
        this.jsonHistory = jsonHistory;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public Compra getProducto() {
        return compra;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
 
    
}
