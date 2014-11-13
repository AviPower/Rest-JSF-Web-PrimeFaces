
package com.sgcv.rest.jsf.web.cliente.extract;

<<<<<<< HEAD
import com.sgcv.rest.jsf.web.bean.CompraBean;
import com.sgcv.rest.jsf.web.model.Compra;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
=======
import com.sgcv.rest.jsf.web.bean.ProductoBean;
import com.sgcv.rest.jsf.web.model.Producto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
>>>>>>> f2962048fee8ba2b7c36a1e44088c99aee84061b
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
/**
 *
 * @author alvarenga
 */
@Named("compraConverter")
<<<<<<< HEAD
@FacesConverter(forClass = Compra.class)
public class CompraConverter  implements Converter {

	@Inject
	private CompraBean compraBean;

	@Override
        public Compra getAsObject(FacesContext context, UIComponent component, String value)
        {
           String numberonly= value.replaceAll("[^0-9]",""); //esto saca el id del entidad de la representacion del objeto entidad
           return compraBean.find(Integer.parseInt(numberonly));
=======
@FacesConverter(forClass = Producto.class)
public class CompraConverter  implements Converter {

	@Inject
	private ProductoBean productoBean;

	@Override
        public Producto getAsObject(FacesContext context, UIComponent component, String value)
        {
           String numberonly= value.replaceAll("[^0-9]",""); //esto saca el id del entidad de la representacion del objeto entidad
           return productoBean.find(Integer.parseInt(numberonly));
>>>>>>> f2962048fee8ba2b7c36a1e44088c99aee84061b
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value)
        {
            if (value != null) {
<<<<<<< HEAD
			Compra compra = (Compra) value;
			return compra.getId() == null ? null : compra.getId().toString();
=======
			Producto producto = (Producto) value;
			return producto.getId() == null ? null : producto.getId().toString();
>>>>>>> f2962048fee8ba2b7c36a1e44088c99aee84061b
		}
		
		return "";
        }
}
