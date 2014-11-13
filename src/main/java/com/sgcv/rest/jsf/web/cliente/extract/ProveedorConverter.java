/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgcv.rest.jsf.web.cliente.extract;

import com.sgcv.rest.jsf.web.bean.ProveedorBean;
import com.sgcv.rest.jsf.web.model.Proveedor;
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
@Named("proveedorConverter")
@FacesConverter(forClass = Proveedor.class)
public class ProveedorConverter  implements Converter {

	@Inject
	private ProveedorBean proveedorBean;

	@Override
        public Proveedor getAsObject(FacesContext context, UIComponent component, String value)
        {
           String numberonly= value.replaceAll("[^0-9]",""); //esto saca el id del entidad de la representacion del objeto entidad
           return proveedorBean.find(Integer.parseInt(numberonly));
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value)
        {
            if (value != null) {
			Proveedor proveedor = (Proveedor) value;
			return proveedor.getId() == null ? null : proveedor.getId().toString();
		}
		
		return "";
        }
}
