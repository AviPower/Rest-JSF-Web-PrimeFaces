/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.cliente.common;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
 
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alvarenga
 */
/**
 * This class encapsulates some basic REST client API.
 * There are many stuff in the internet how to work with RESTful Client API.
 * These are basics. But even though the subject seems to be trivial,
 * there are hurdles, especially for beginners.
 * In this post I will try to summurize my know-how how I did this in real projects.
 * I usually use Jersey (reference implementation for building RESTful services).
 *  In this post, I will call a real remote service from JSF beans. Let's write a session scoped bean RestClient. 
 */
@ManagedBean
@SessionScoped
public class RestClient implements Serializable {
 
    private transient Client client;
 
    public String SERVICE_BASE_URI;
 
    @PostConstruct
    protected void initialize() {
        FacesContext fc = FacesContext.getCurrentInstance();
        SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");
 
        client = Client.create();
    }
 
    public WebResource getWebResource(String relativeUrl) {
        if (client == null) {
            initialize();
        }
 
        return client.resource(SERVICE_BASE_URI + relativeUrl);
    }
 
    public ClientResponse clientGetResponse(String relativeUrl) {
        WebResource webResource = client.resource(SERVICE_BASE_URI + relativeUrl);
        return webResource.accept("application/json").get(ClientResponse.class);
    }
}
