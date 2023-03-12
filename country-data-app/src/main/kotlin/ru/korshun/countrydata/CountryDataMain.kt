package ru.korshun.countrydata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class CountryDataMain
fun main(args: Array<String>) {
  runApplication<CountryDataMain>(*args)
}