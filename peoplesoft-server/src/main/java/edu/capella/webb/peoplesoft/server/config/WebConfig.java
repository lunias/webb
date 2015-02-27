package edu.capella.webb.peoplesoft.server.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import edu.capella.webb.peoplesoft.server.util.Constant;

@Configuration
@EnableHypermediaSupport(type = { HypermediaType.HAL })
public class WebConfig extends WebMvcConfigurerAdapter implements ServletContextInitializer, EmbeddedServletContainerCustomizer {

	private final Logger log = LoggerFactory.getLogger(WebConfig.class);
	
	@Autowired
	Environment environment;
	
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		
        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        mappings.add("html", "text/html;charset=utf-8");
        mappings.add("json", "text/html;charset=utf-8");
        
        container.setMimeMappings(mappings);
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

		configurer
			.favorParameter(true)
			.parameterName("mediaType")
			.ignoreAcceptHeader(true)
			.useJaf(false)
			.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType("xml", MediaType.APPLICATION_XML)
			.mediaType("json", MediaType.APPLICATION_JSON);		
	}

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		
        if (environment.acceptsProfiles(Constant.SPRING_PROFILE_DEVELOPMENT)) {
            initH2Console(servletContext);
        }		
	}
	
    private void initH2Console(ServletContext servletContext) {
    	
        log.debug("Initializing H2 console...");
        
        ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console", new org.h2.server.web.WebServlet());
        h2ConsoleServlet.addMapping("/console/*");
        h2ConsoleServlet.setInitParameter("-properties", "src/main/resources");
        h2ConsoleServlet.setLoadOnStartup(1);
    }	

}
