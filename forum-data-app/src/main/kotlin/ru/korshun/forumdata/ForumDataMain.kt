package ru.korshun.forumdata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class ForumDataMain

fun main(args: Array<String>) {
  runApplication<ForumDataMain>(*args)
}