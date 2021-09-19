package com.example.graphql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux

@SpringBootApplication
class GraphqlApplication

fun main(args: Array<String>) {
    runApplication<GraphqlApplication>(*args)
}

@Controller
class GreetingGraphqlController {

    @QueryMapping
    fun customers() = Flux.just(Customer(1, "A"), Customer(2, "B "))

    @SchemaMapping(typeName = "Customer")
    fun orders(customer: Customer) = listOf(Order(1, customer.id), Order(2, customer.id))
}

data class Order(val id: Int, val customerId: Int)
data class Customer(val id: Int, val name: String)