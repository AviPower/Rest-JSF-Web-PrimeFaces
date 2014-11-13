
package com.sgcv.rest.jsf.web.cliente.extract;

import com.sgcv.rest.jsf.web.bean.ProductoBean;
import com.sgcv.rest.jsf.web.model.Producto;
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
@Named("productoConverter")
@FacesConverter(forClass = Producto.class)
public class ProductoConverter  implements Converter {

	@Inject
	private ProductoBean productoBean;

	@Override
        public Producto getAsObject(FacesContext context, UIComponent component, String value)
        {
           String numberonly= value.replaceAll("[^0-9]",""); //esto saca el id del entidad de la representacion del objeto entidad
           return productoBean.find(Integer.parseInt(numberonly));
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value)
        {
            if (value != null) {
			Producto producto = (Producto) value;
			return producto.getId() == null ? null : producto.getId().toString();
		}
		
		return "";
        }
}
