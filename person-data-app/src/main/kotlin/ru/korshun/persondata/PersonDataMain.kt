package ru.korshun.persondata

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class PersonDataMain

val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
  runApplication<PersonDataMain>(*args)
}