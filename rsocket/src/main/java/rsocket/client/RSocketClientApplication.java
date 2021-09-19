package rsocket.client;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import rsocket.GreetingRequest;
import rsocket.GreetingResponse;

@SpringBootApplication
public class RSocketClientApplication {

    @Bean
    RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
        return builder.tcp("127.0.0.1", 8181);
    }

    @Bean
    ApplicationRunner runner(RSocketRequester rSocketRequester) {
        return event -> {
            System.out.println("running the client code");
            var response = rSocketRequester.route("greetings")
                    .data(new GreetingRequest("World"))
                    .retrieveMono(GreetingResponse.class);
            response.subscribe(System.out::println);
        };
    }

    public static void main(String args[]) throws Exception {
        SpringApplication.run(RSocketClientApplication.class, args);
        System.in.read();
    }
}
