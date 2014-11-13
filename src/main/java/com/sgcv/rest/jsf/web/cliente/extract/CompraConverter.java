
package com.sgcv.rest.jsf.web.cliente.extract;

import com.sgcv.rest.jsf.web.bean.CompraBean;
import com.sgcv.rest.jsf.web.model.Compra;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
/**
 *
 * @author alvarenga
 */
@Named("compraConverter")
@FacesConverter(forClass = Compra.class)
public class CompraConverter  implements Converter {

	@Inject
	private CompraBean compraBean;

	@Override
        public Compra getAsObject(FacesContext context, UIComponent component, String value)
        {
           String numberonly= value.replaceAll("[^0-9]",""); //esto saca el id del entidad de la representacion del objeto entidad
           return compraBean.find(Integer.parseInt(numberonly));
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value)
        {
            if (value != null) {
			Compra compra = (Compra) value;
			return compra.getId() == null ? null : compra.getId().toString();
		}
		
		return "";
        }
}
