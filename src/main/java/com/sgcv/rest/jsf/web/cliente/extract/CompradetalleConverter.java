
package com.sgcv.rest.jsf.web.cliente.extract;

import com.sgcv.rest.jsf.web.bean.CompraBean;
import com.sgcv.rest.jsf.web.bean.CompradetalleBean;
import com.sgcv.rest.jsf.web.model.Compradetalle;
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
@Named("compradetalleConverter")
@FacesConverter(forClass = Compradetalle.class)
public class CompradetalleConverter  implements Converter {

	@Inject
	private CompradetalleBean compradetalleBean;

	@Override
        public Compradetalle getAsObject(FacesContext context, UIComponent component, String value)
        {
           if (value == null || "".equals(value.trim())) {
			return null;
		}

		Integer id = null;
		
		try {
			id = Integer.valueOf(value);

			Compradetalle compradetalle = compradetalleBean.find(id);

			if (compradetalle == null) {
				throw new ConverterException("No associated Moneda for value: " + id);
			}

			return compradetalle;

		} catch (Exception e) {
			throw new ConverterException("No associated Moneda for value " + id, e);
		}
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value)
        {
            if (value != null) {
			Compradetalle compra = (Compradetalle) value;
			return compra.getId() == null ? null : compra.getId().toString();
		}
		
		return "";
        }
}
