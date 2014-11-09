/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.cliente.bbean;

import com.sgcv.rest.jsf.web.cliente.util.GsonConverter;
import com.sgcv.rest.jsf.web.cliente.common.RestClient;
import com.sgcv.rest.jsf.web.model.Cliente;
import com.sun.jersey.api.client.ClientResponse;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alvarenga
 */
/**http://ovaraksin.blogspot.com/2012/12/get-post-with-restful-client-api.html
 * Bean getting history of the last extracted documents. 
  * Tenemos la intención de recibir los recursos en formato JSON y convertirlos en objetos Java. 
  * El próximo bean muestra cómo hacer esta tarea para peticiones GET. 
  * Los conversos bean ClienteBBean reciben JSON en un objeto Ciemte mediante el uso de GsonConverter. 
  * Cliente es un simple POJO y GsonConverter es una instancia singleton que envuelve Gson.
 */
//Conseguir la historia de los ultimos elementos extraidos
@ManagedBean
@ViewScoped
public class ClienteBBean implements Serializable {
    
    /*anotation forma de inyectar en el controlador,
    ManagedProperty utilizar ese bean almacenado
    en la sesión actual de modo a pueda tener acceso
    a la totalidad de sus valores generador por RestClient.*/
    @ManagedProperty(value = "#{restClient}")
    private RestClient restClient;
    
    //Objeto pojo Cliente
    private List<Cliente> documents;
    private String jsonHistory;
 
    public List<Cliente> getDocuments() {
        if (documents != null) {
            return documents;
        }
        //Envia el path de peticion get de cliente, listado de clientes
        ClientResponse response = restClient.clientGetResponse("cliente");
 
        //verifica que no haya error con la pagina.. si es 200 caso de exito, sino fallo
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed service call: HTTP error code : " + response.getStatus());
        }
 
        // get cliente as JSON, el json se guarda como string
        jsonHistory = response.getEntity(String.class);
 
        // convert to Java array / list of Cliente instances, Convierte el json a una Lista de Clientes
        //fromJson pasa de la notacion json a java, Si fuera en caso contrario se usaria toJson
        Cliente[] docs = GsonConverter.getGson().fromJson(jsonHistory, Cliente[].class);
        documents = Arrays.asList(docs);
 
        return documents;
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

    public void setDocuments(List<Cliente> documents) {
        this.documents = documents;
    }

    public void setJsonHistory(String jsonHistory) {
        this.jsonHistory = jsonHistory;
    }
 
 
}
