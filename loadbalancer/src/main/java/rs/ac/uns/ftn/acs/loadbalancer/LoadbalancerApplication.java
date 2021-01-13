package rs.ac.uns.ftn.acs.loadbalancer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class LoadbalancerApplication {

    private final Environment environment;

    public LoadbalancerApplication(Environment environment) {
        this.environment = environment;
    }

    private static final Logger log = LoggerFactory.getLogger(LoadbalancerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LoadbalancerApplication.class, args);
    }

    @GetMapping("/greeting")
    public String greet() {
        log.info("Access /greeting");

        List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");

        Random rand = new Random();

        int randomNum = rand.nextInt(greetings.size());

        return greetings.get(randomNum) + " [PORT: " + environment.getProperty("local.server.port") + "]";
    }

    @GetMapping("/")
    public String home() {
        log.info("Access");
        return "Hi!" + " [PORT: " + environment.getProperty("local.server.port") + "]";
    }
}
