package com.example.mvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.Assert
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping


@SpringBootApplication
class MvcApplication

fun main(args: Array<String>) {
    runApplication<MvcApplication>(*args)
}

@Controller
class GreetingsMvcController {

    @ExceptionHandler
    fun onException(ex: Exception) = println("there was an exception: ${ex}")

    @GetMapping("/greeting")
    fun greetingRender(model: Model): String {
        model.addAttribute("greeting", Greeting())
        return "greeting"
    }

    @PostMapping("/greeting")
    fun greetingSubmit(@ModelAttribute greeting: Greeting, model: Model): String {
        Assert.isTrue(Character.isUpperCase(greeting.name!!.first())) { "the name must start with a capital letter" }
        model.addAttribute("greeting", greeting)
        return "result"
    }
}


data class Greeting(var name: String? = null)