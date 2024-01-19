package com.test.form.controller;

import com.test.form.model.Response;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Base64;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.OPTIONS,RequestMethod.GET,
        RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private JasperReport jasperReport;

    @PostMapping("/generate")
    public Response generateReport(@RequestBody Map<String, Object> reportParameters,
                                   HttpServletResponse response) throws JRException {
        // Lógica para generar el informe utilizando reportParameters
        JasperPrint jasperPrint = generateJasperPrint(reportParameters);

        // Configurar la respuesta HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "report.pdf");

        // Convertir el informe a bytes y enviarlo como respuesta
        byte[] reportBytes = JasperExportManager.exportReportToPdf(jasperPrint);
        String base64String = encodeToBase64(reportBytes);
        Response resp = new Response();
        resp.setData(base64String);
        //return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
        return resp;
    }

    public JasperPrint generateJasperPrint(Map<String, Object> reportParameters) {
        try {
            // Cargar el archivo .jrxml o .jasper
            ClassPathResource resource = new ClassPathResource("reports/report.jrxml");
            InputStream inputStream = resource.getInputStream();

            String filePath = "export/";

            // Obtener la ruta relativa del recurso usando ClassPathResource
            Resource resourcePath = new ClassPathResource(filePath);
            String relativePath = resourcePath.getURL().getPath();
            // Compilar el informe si es un archivo .jrxml
            JasperReport jasperReport;
            if (resource.getFilename().endsWith(".jrxml")) {
                jasperReport = JasperCompileManager.compileReport(inputStream);
                // Guardar el informe compilado para mejorar el rendimiento en futuras ejecuciones
                JRSaver.saveObject(jasperReport, relativePath+"/report.jasper");
            } else {
                // Si ya es un archivo .jasper, simplemente cargarlo
                jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
            }

            // Rellenar el informe con datos y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParameters, new JREmptyDataSource());

            return jasperPrint;
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente en tu aplicación
            return null;
        }
    }

    private static String encodeToBase64(byte[] byteArray) {
        // Utilizar la clase Base64 para realizar la codificación
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
