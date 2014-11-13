/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.cliente.extract;

import com.sgcv.rest.jsf.web.bean.ClienteBean;
import com.sgcv.rest.jsf.web.model.Cliente;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author alvarenga
 */
@Named("clienteConverter")
@FacesConverter(forClass = Cliente.class)
public class ClienteConverter  implements Converter{
    @Inject
    private ClienteBean clienteBean;

    @Override
    public Cliente getAsObject(FacesContext context, UIComponent component, String value)
    {
       if (value == null || "".equals(value.trim())) {
			return null;
		}

		Integer id = null;
		
		try {
			id = Integer.valueOf(value);

			Cliente cliente = clienteBean.find(id);

			if (cliente == null) {
				throw new ConverterException("No associated Moneda for value: " + id);
			}

			return cliente;

		} catch (Exception e) {
			throw new ConverterException("No associated Moneda for value " + id, e);
		}
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        
        if (value != null) {
                    Cliente cliente = (Cliente) value;
                    return cliente.getId() == null ? null : cliente.getId().toString();
            }

            return "";
    }
}
