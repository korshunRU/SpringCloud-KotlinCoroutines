package ru.korshun.persondata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class PersonDataMain

fun main(args: Array<String>) {
  runApplication<PersonDataMain>(*args)
}