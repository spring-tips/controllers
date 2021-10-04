package com.example.alltogethernow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@SpringBootApplication
public class AlltogethernowApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlltogethernowApplication.class, args);
    }

}

record Greeting(String name) {
}


@RestController
class GreetingHttpRestController {


}

@RSocketController
class GreetingRSocketController {

}

@GraphqlController
class GreetingGraphqlController {

}


@MvcController
class GreetingHttpMvcController {

}

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@interface MvcController {
    @AliasFor(
            annotation = Controller.class
    )
    String value() default "";
}


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@interface GraphqlController {
    @AliasFor(
            annotation = Controller.class
    )
    String value() default "";
}

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@interface RSocketController {
    @AliasFor(
            annotation = Controller.class
    )
    String value() default "";
}
