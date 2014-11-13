
package com.sgcv.rest.jsf.web.cliente.util.report;

/**
 *
 * @author alvarenga
 */
import com.sgcv.rest.jsf.web.cliente.util.JsfUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import org.hibernate.Session;


@Named
@RequestScoped
@ManagedBean
public class EmitirFactura implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	@PostConstruct
	   public void start() {
		this.id=(Integer) 1;
	      }
	
	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("P_ID", this.id);
		
		ActionReport executor = new ActionReport("/reporte/productos.jasper",
				this.response, parametros, "FacturaEmitida.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isreporteGenerado()) {
			facesContext.responseComplete();
		} else {
			JsfUtils.addErrorMessage("Este reporte no genera Datos");
		}
	}
		
	@NotNull
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

}
