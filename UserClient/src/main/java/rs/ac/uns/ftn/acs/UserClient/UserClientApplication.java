package rs.ac.uns.ftn.acs.UserClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class UserClientApplication {

    private final WebClient.Builder loadBalancedWebClientBuilder;
    private final ReactorLoadBalancerExchangeFilterFunction lbfunction;

    public UserClientApplication(WebClient.Builder loadBalancedWebClientBuilder,
                                 ReactorLoadBalancerExchangeFilterFunction lbfunction) {
        this.loadBalancedWebClientBuilder = loadBalancedWebClientBuilder;
        this.lbfunction = lbfunction;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserClientApplication.class, args);
    }

    @RequestMapping("/hi")
    public Mono<String> hi(@RequestParam(value = "name", defaultValue = "Marko") String name) {
        return loadBalancedWebClientBuilder.build().get().uri("http://say-hello/greeting")
                .retrieve().bodyToMono(String.class)
                .map(greeting -> String.format("%s, %s!", greeting, name));
    }

    @RequestMapping
    public Mono<String> hello(@RequestParam(value = "name", defaultValue = "Vladimir") String name) {
        return WebClient.builder()
                .filter(lbfunction)
                .build().get().uri("http://say-hello/greeting")
                .retrieve().bodyToMono(String.class)
                .map(greeting -> String.format("%s, %s!", greeting, name));
    }

}
