package com.pa165.ddtroops.soap.app;

import java.util.List;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Our configuration class note @EnableWs
 * http://docs.spring.io/spring-ws/docs/current/api/org/springframework/ws/config/annotation/EnableWs.html
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    /**
     * Creation of the MessageDispatcherServlet, note that it is different from
     * a DispatcherServlet see
     * http://docs.spring.io/spring-ws/site/reference/html/server.html in
     * particular if you need to use it in a standard DispatcherServlet (section
     * 5.3.2)
     *
     * @param applicationContext
     * @return
     */
    @Bean
    public ServletRegistrationBean dispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    /**
     *
     * WSDL definition from the provided schema (in our case books) you can find
     * the WSDL file at http://localhost:8080/ws/books.wsdl
     *
     */
    @Bean(name = "heroes")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema heroesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("HeroesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://pa165.com/ddtroops/soap");
        wsdl11Definition.setSchema(heroesSchema);
        return wsdl11Definition;
    }

    /**
     * see
     * http://docs.spring.io/spring-ws/site/spring-xml/apidocs/org/springframework/xml/xsd/SimpleXsdSchema.html
     *
     */
    @Bean
    public XsdSchema heroesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("heroes.xsd"));
    }

    @Bean
    public PayloadValidatingInterceptor myPayLoadInterceptor() {
        PayloadValidatingInterceptor interceptor = new PayloadValidatingInterceptor();
        interceptor.setXsdSchema(this.heroesSchema());
        interceptor.setValidateRequest(true);
        interceptor.setValidateResponse(true);
        return interceptor;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(this.myPayLoadInterceptor());
    }

}
