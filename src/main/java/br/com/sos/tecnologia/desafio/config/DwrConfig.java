package br.com.sos.tecnologia.desafio.config;

import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DwrConfig {
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
       DwrSpringServlet servlet = new DwrSpringServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(servlet, "/dwr/*");
        // Set to true to enable DWR to debug and enter the test page.
        registrationBean.addInitParameter("debug", "true");
        //pollAndCometEnabled set to true to increase the loadability of the server, although DWR has a mechanism to protect the server from overload.
        registrationBean.addInitParameter("pollAndCometEnabled", "true");
        
        registrationBean.addInitParameter("activeReverseAjaxEnabled", "true");
        registrationBean.addInitParameter("maxWaitAfterWrite", "60");
        registrationBean.addInitParameter("useHttpOnly", "false");
        return registrationBean;
    }
}