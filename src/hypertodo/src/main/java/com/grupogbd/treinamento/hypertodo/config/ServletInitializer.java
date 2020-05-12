package com.grupogbd.treinamento.hypertodo.config;

import com.grupogbd.treinamento.hypertodo.HypertodoApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HypertodoApplication.class);
    }

}
