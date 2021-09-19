package rsocket.service;
import rsocket.GreetingRequest ;
import rsocket.GreetingResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@SpringBootApplication
public class RSocketServiceApplication {

    public static void main(String[] args) {
        System.setProperty("spring.rsocket.server.port","8181");
        SpringApplication.run(RSocketServiceApplication.class, args);
    }

}

@Controller
class GreetingsRSocketController {

    @MessageExceptionHandler
    public String handleException(IllegalArgumentException e) {
        var message = ("an exception occurred! " + NestedExceptionUtils.getMostSpecificCause(e));
        System.out.println(message);
        return message;
    }

    @MessageMapping("greetings")
    GreetingResponse   greet(GreetingRequest request) {
        return new GreetingResponse("Hello, " + request.name() + "!");
    }
}

