package ru.korshun.countrydata

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class CountryDataMain

val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
  runApplication<CountryDataMain>(*args)
}