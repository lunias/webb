package edu.capella.webb.hystrix.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableHystrixDashboard
@Controller
public class Application {

    @RequestMapping("/")
    public String hystrixDashboard() {
        return "forward:/hystrix";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }	
	
}
