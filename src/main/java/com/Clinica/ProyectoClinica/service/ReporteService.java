package com.Clinica.ProyectoClinica.service;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReporteService {

	@Autowired
	private DataSource dataSource;

	public byte[] generarReporteHistorialPDF() throws Exception {
		InputStream jrxml = new ClassPathResource("reportes/HClinicos.jrxml").getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(jrxml);
		Connection conn = DataSourceUtils.getConnection(dataSource);
		try {
			JasperPrint print = JasperFillManager.fillReport(jasperReport, new HashMap<>(), conn);
			return JasperExportManager.exportReportToPdf(print);
		} finally {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

	public byte[] generarReportePorPacientePDF(Long pacienteId) throws Exception {
		InputStream jrxml = new ClassPathResource("reportes/FichaPaciente.jrxml").getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(jrxml);
		Connection conn = DataSourceUtils.getConnection(dataSource);
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("PACIENTE_ID", pacienteId);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, params, conn);
			return JasperExportManager.exportReportToPdf(print);
		} finally {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

	public byte[] generarGraficoCitasAnioPDF() throws Exception {
		InputStream jrxml = new ClassPathResource("reportes/citasPorAnio.jrxml").getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(jrxml);
		Connection conn = DataSourceUtils.getConnection(dataSource);
		try {
			Map<String, Object> params = new HashMap<>(); // Si quieres parámetros, agrégalos aquí
			JasperPrint print = JasperFillManager.fillReport(jasperReport, params, conn);
			return JasperExportManager.exportReportToPdf(print);
		} finally {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

}
