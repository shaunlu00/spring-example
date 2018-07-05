package com.shaunlu.github.lightweb;

import com.shaunlu.github.lightweb.config.APPConfiguration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.List;

public class WebInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(APPConfiguration.class);
        ctx.refresh();

        //create DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(ctx);

        // Register and map the Servlet
        ServletRegistration.Dynamic registration = servletContext.addServlet("rest", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/*");
    }
}
