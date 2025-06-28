package com.Clinica.ProyectoClinica.service;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReporteService {

    @Autowired
    private DataSource dataSource;

    public byte[] generarReporteHistorialPDF() throws Exception {
        // 1. Compilar el jrxml
        JasperReport jasperReport = JasperCompileManager.compileReport(
            getClass().getResourceAsStream("/reportes/ReportesHistorialPDF.jrxml")
        );

        // 2. Parámetros (si tu reporte los usa)
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Reporte de Historial Médico"); // ejemplo

        // 3. Obtener la conexión y llenar el reporte
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
            // 4. Exportar a PDF en byte[]
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
    }
}
