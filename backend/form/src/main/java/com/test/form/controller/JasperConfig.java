package com.test.form.controller;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class JasperConfig {

    @Bean
    public JasperReport jasperReport() throws Exception {

        //String filePath = "reports/report.jrxml";
        ClassPathResource resource = new ClassPathResource("reports/report.jrxml");
        //ClassPathResource resource = new ClassPathResource(filePath);
        InputStream inputStream = resource.getInputStream();
        return JasperCompileManager.compileReport(inputStream);
    }
}
