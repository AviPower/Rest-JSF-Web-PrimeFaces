package com.sgcv.rest.jsf.web.cliente.util.report;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.hibernate.jdbc.Work;

public class ActionReport implements Work {
	private String caminoReporte;
	private HttpServletResponse response;
	private Map<String, Object> parametros;
	private String nombreArchivoSalida;
	
	private boolean reporteGenerado;
	
	public ActionReport(String caminoReporte,
			HttpServletResponse response, Map<String, Object> parametros,
			String nombreArchivoSalida) {
		this.caminoReporte = caminoReporte;
		this.response = response;
		this.parametros = parametros;
		this.nombreArchivoSalida = nombreArchivoSalida;
		
		this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		try {
			InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminoReporte);
			
			JasperPrint print = JasperFillManager.fillReport(relatorioStream, this.parametros, connection);
			this.reporteGenerado = print.getPages().size() > 0;
			
			if (this.reporteGenerado) {
				JRExporter exportador = new JRPdfExporter();
				exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
				
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"" 
						+ this.nombreArchivoSalida  + "\"");
				
				exportador.exportReport();
			}
		} catch (Exception e) {
			throw new SQLException("Error para ejecutar Reporte" + this.caminoReporte, e);
		}
	}

	public boolean isreporteGenerado() {
		return reporteGenerado;
	}

}
