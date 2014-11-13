/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.cliente.util;

import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
/**
 *
 * @author alvarenga
 */
public abstract class JsfUtils {
	
	public static <T> T getParamFromUrl(String nameParam, Class<T> cls){
		return cls.cast(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(nameParam));
	}
	
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	public static void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	public static boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}
	
	public static boolean isNotPostback() {
		return !isPostback();
	}
}
