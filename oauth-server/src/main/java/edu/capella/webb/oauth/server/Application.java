package edu.capella.webb.oauth.server;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import edu.capella.webb.oauth.server.util.Constant;

@EnableHypermediaSupport(type = { HypermediaType.HAL })
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter implements ServletContextInitializer, EmbeddedServletContainerCustomizer {

	@Autowired
	private Environment environment;	
	
	private final Logger log = LoggerFactory.getLogger(Application.class);	
	
    public static void main(String[] args) {
    	
    	SpringApplication app = new SpringApplication(Application.class);
    	app.setShowBanner(true);
    	
    	SimpleCommandLinePropertySource cmd = new SimpleCommandLinePropertySource(args);
    	addDefaultProfile(app, cmd);
    	
    	app.run(args);
    }
    
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
    	
        if (!source.containsProperty("spring.profiles.active")) {
            app.setAdditionalProfiles(Constant.SPRING_PROFILE_STAND_ALONE);
        }
    }    
    
    @PostConstruct
    public void initApplication() {
    	if (environment.getActiveProfiles().length == 0) {
    		log.warn("No spring profiles configured. Running with default configuration");
    	} else {
    		log.info("Running with spring profile(s): {}", Arrays.toString(environment.getActiveProfiles()));
    	}
    }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/oauth/confirm_access").setViewName("authorize");
	}
	
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
		
        if (environment.acceptsProfiles(Constant.SPRING_PROFILE_DEVELOPMENT, Constant.SPRING_PROFILE_STAND_ALONE)) {
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
