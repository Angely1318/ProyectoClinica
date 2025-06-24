package com.Clinica.ProyectoClinica.controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.Clinica.ProyectoClinica.entity.HistorialClinico;
import com.Clinica.ProyectoClinica.service.HistorialClinicoService;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReporteHistorialCController {

    @Autowired
    private HistorialClinicoService historialService;

    @GetMapping("/reporte/historial")
    public ResponseEntity<byte[]> generarReporte() {
        try {
            // 1. Cargar el .jrxml (o usa .jasper si ya lo compilaste antes)
            InputStream input = new ClassPathResource("reportes/Coffee_Landscape.jrxml").getInputStream();
            JasperReport reporte = JasperCompileManager.compileReport(input);

            // 2. Obtener los datos
            List<HistorialClinico> lista = historialService.listarTodos();
            JRBeanCollectionDataSource datos = new JRBeanCollectionDataSource(lista);

            // 3. Parámetros si el reporte lo requiere
            Map<String, Object> parametros = new HashMap<>();

            // 4. Llenar el reporte
            JasperPrint print = JasperFillManager.fillReport(reporte, parametros, datos);

            // 5. Exportar a PDF
            byte[] pdf = JasperExportManager.exportReportToPdf(print);

            // 6. Retornar PDF como respuesta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "reporte_historial.pdf");

            return ResponseEntity.ok().headers(headers).body(pdf);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
