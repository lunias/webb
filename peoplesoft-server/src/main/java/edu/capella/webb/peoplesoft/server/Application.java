package edu.capella.webb.peoplesoft.server;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import edu.capella.webb.peoplesoft.server.util.Constant;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class Application {

	@Autowired
	Environment environment;
	
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
	
}
