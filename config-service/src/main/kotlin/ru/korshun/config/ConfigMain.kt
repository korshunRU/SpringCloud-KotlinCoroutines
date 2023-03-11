package ru.korshun.config

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfigMain

val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
  runApplication<ConfigMain>(*args)
}