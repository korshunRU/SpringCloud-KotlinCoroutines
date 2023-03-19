package ru.korshun.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfigMain

fun main(args: Array<String>) {
  runApplication<ConfigMain>(*args)
}