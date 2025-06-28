package com.Clinica.ProyectoClinica.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.Clinica.ProyectoClinica.service.ReporteService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/historial")
    public ResponseEntity<byte[]> generarPDF() throws Exception {
        byte[] data = reporteService.generarReporteHistorialPDF();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename("historial.pdf").build());

        return ResponseEntity.ok().headers(headers).body(data);
    }
}
