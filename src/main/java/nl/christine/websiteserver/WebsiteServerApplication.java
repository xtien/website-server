package nl.christine.websiteserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EntityScan( basePackages = {"nl.christine.websiteserver.model"})
public class WebsiteServerApplication {

     public static void main(String[] args) {
        SpringApplication.run(WebsiteServerApplication.class, args);
    }

}
