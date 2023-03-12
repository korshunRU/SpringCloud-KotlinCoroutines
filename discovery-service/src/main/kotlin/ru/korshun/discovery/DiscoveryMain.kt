package ru.korshun.discovery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class DiscoveryMain
fun main(args: Array<String>) {
  runApplication<DiscoveryMain>(*args)
}