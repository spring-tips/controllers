package com.example.ws

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@SpringBootApplication
class WsApplication

fun main(args: Array<String>) {
    runApplication<WsApplication>(*args)
}

data class GreetingsRequest(val name: String)

data class GreetingsResponse(val message: String)

@Controller
class GreetingsWebSocketController {

    @ExceptionHandler
    fun handleException(e: Exception) = println("oops! ${e} happened!")

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    fun greeting(request: GreetingsRequest) = GreetingsResponse("Hello, ${request.name}!")
    

}


@Configuration
@EnableWebSocketMessageBroker
class MyWebSocketConfiguration : WebSocketMessageBrokerConfigurer {

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/chat").withSockJS()
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic")
        registry.setApplicationDestinationPrefixes("/app")
    }
}