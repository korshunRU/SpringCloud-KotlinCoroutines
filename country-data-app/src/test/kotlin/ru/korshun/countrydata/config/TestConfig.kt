package ru.korshun.countrydata.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfig {

  @Bean
  fun objectMapper(): ObjectMapper =
    ObjectMapper().registerModule(kotlinModule())

}