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
 *http://www.mkyong.com/webservices/jax-rs/restful-java-client-with-jersey-client/
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
 
    /* utilizar las API de cliente Jersey para crear un cliente 
    RESTful Java para realizar "GET" y "POST" solicitudes a servicio REST*/
    private transient Client client;
    
    /*
        web.xml
    <context-param>
        <param-name>metadata.serviceBaseURI</param-name>
        <param-value>http://localhost:8080/Rest-JSF-Web-PrimeFaces/webresources/</param-value>
    </context-param>
    Referencia al path para acceder a los servicios rest
    */
    public String SERVICE_BASE_URI;
 
    /**
     * FacesContext contiene toda la información por solicitud del Estado en relación 
     * con la tramitación de una sola solicitud JavaServer Faces, y la prestación de la 
     * respuesta correspondiente. Se transmite a, y potencialmente modificado por, cada 
     * fase del ciclo de vida de procesamiento de la solicitud.
     **/
    @PostConstruct
    protected void initialize() {
        /*getCurrentInstance () Devuelve el FacesContext instancia para la
        solicitud de que se está procesando por el subproceso actual.*/
        FacesContext fc = FacesContext.getCurrentInstance();
        
        /*Devuelva el ExternalContext ejemplo para este FacesContext instancia.
        Es válido para llamar a este método durante el inicio de la aplicación o el apagado
        OBTIENE EL VALOR CONSTANTE DECLARADO EN EL ARCHIVO web.xml
        */
        SERVICE_BASE_URI = fc.getExternalContext().getInitParameter("metadata.serviceBaseURI");
 
        client = Client.create();
    }
    
    /*Recibe la solicitud, Si client jersey es null lo inicializa*/
    public WebResource getWebResource(String relativeUrl) {
        if (client == null) {
            initialize();
        }
        /*Devuelve la recepcion del path de llamada, ejemplo
        http://localhost:8080/Rest-JSF-Web-PrimeFaces/webresources/Producto*/
        return client.resource(SERVICE_BASE_URI + relativeUrl);
    }
    /*Se especifica el tipo MIME para la respuesta en este caso json*/
    public ClientResponse clientGetResponse(String relativeUrl) {
        WebResource webResource = client.resource(SERVICE_BASE_URI + relativeUrl);
        return webResource.accept("application/json").get(ClientResponse.class);
    }
}
