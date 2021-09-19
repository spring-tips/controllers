package com.example.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}

record Greeting(String name) {
}

@Controller
@ResponseBody
class GreetingRestController {

    @ExceptionHandler
    ResponseEntity<?> handleException(Exception exception) {
        System.out.println("the exception is " + NestedExceptionUtils.getMostSpecificCause(exception));
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/greetings/{name}")
    Mono<Greeting> greeting(@PathVariable String name) {
        Assert.isTrue(Character.isUpperCase(name.charAt(0)), () -> "the name must start with a capital letter");
        return Mono.just(new Greeting("Hello, " + name + "!"));
    }
}