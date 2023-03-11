package ru.korshun.discovery

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class DiscoveryMain

val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
  runApplication<DiscoveryMain>(*args)
}