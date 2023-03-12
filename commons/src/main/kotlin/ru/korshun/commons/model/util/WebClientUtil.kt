package ru.korshun.commons.model.util

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

suspend inline fun <reified T> WebClient.getAsync(
  path: String
): T = this.get()
  .uri { builder -> builder.path(path).build() }
  .retrieve()
  .awaitBody()