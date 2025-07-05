package com.Clinica.ProyectoClinica.controller;

import com.Clinica.ProyectoClinica.service.ReporteService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/reporte")
public class ReporteController {

	@Autowired
	private ReporteService reporteService;

	@GetMapping(value = "/historial", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> generarPDFGeneral() throws Exception {
		byte[] data = reporteService.generarReporteHistorialPDF();
		ContentDisposition cd = ContentDisposition.inline().filename("historial_clinico.pdf").build();
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, cd.toString()).body(data);
	}

	@GetMapping(value = "/paciente/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> generarPDFPorPaciente(@PathVariable Long id) throws Exception {
		byte[] data = reporteService.generarReportePorPacientePDF(id);
		ContentDisposition cd = ContentDisposition.inline().filename("historial_paciente.pdf").build();
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, cd.toString()).body(data);
	}
	@GetMapping(value = "/citas-anio", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> generarGraficoCitas() throws Exception {
	    byte[] data = reporteService.generarGraficoCitasAnioPDF();
	    ContentDisposition cd = ContentDisposition.inline().filename("grafico_citas_anio.pdf").build();
	    return ResponseEntity.ok()
	            .contentType(MediaType.APPLICATION_PDF)
	            .header(HttpHeaders.CONTENT_DISPOSITION, cd.toString())
	            .body(data);
	}


}
