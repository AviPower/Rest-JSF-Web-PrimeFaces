/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.cliente.history;

import com.sgcv.rest.jsf.web.cliente.util.GsonConverter;
import com.sgcv.rest.jsf.web.cliente.common.RestClient;
import com.sgcv.rest.jsf.web.model.Cliente;
import com.sun.jersey.api.client.ClientResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alvarenga
 */
/**http://ovaraksin.blogspot.com/2012/12/get-post-with-restful-client-api.html
 * Bean getting history of the last extracted documents.
 * Furthermore, we wrote two methods to receive remote resources.
 * We intend to receive resources in JSON format and convert them to Java objects.
 * The next bean demonstrates how to do this task for GET requests.
 * The bean HistoryBean converts received JSON to a Document object by using GsonConverter. 
 * The last two classes will not be shown here (they don't matter).
 * Cliente is a simple POJO and GsonConverter is a singleton instance which wraps Gson. 
 */
@ManagedBean
@ViewScoped
public class HistoryBean implements Serializable {
 
    @ManagedProperty(value = "#{restClient}")
    private RestClient restClient;
 
    private List<Cliente> documents;
    private String jsonHistory;
 
    public List<Cliente> getDocuments() {
        if (documents != null) {
            return documents;
        }
 
        ClientResponse response = restClient.clientGetResponse("history");
 
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed service call: HTTP error code : " + response.getStatus());
        }
 
        // get history as JSON
        jsonHistory = response.getEntity(String.class);
 
        // convert to Java array / list of Document instances
        Cliente[] docs = GsonConverter.getGson().fromJson(jsonHistory, Cliente[].class);
        documents = Arrays.asList(docs);
 
        return documents;
    }
 
    // getter / setter
 
}
