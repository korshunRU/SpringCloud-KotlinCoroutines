package ru.korshun.countrydata.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import io.netty.channel.ChannelOption
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.InsecureTrustManagerFactory
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.ClientCodecConfigurer
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.Connection
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit


@Configuration
class WebClientConfig {

  @Value("\${integration.base-url}")
  private lateinit var baseUrl: String

  @Bean
  fun objectMapper(): ObjectMapper =
    ObjectMapper()
      .registerModule(kotlinModule())
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  @Bean
  fun webClient(httpClient: HttpClient, objectMapper: ObjectMapper): WebClient {
    val exchangeStrategies = ExchangeStrategies.builder()
      .codecs { configurer: ClientCodecConfigurer ->
        configurer.defaultCodecs().jackson2JsonEncoder(Jackson2JsonEncoder(objectMapper))
        configurer.defaultCodecs().jackson2JsonDecoder(Jackson2JsonDecoder(objectMapper)) }
      .build()
    return WebClient.builder()
      .baseUrl(baseUrl)
      .clientConnector(ReactorClientHttpConnector(httpClient))
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .exchangeStrategies(exchangeStrategies)
      .build()
  }

  @Bean
  fun httpClient(sslContext: SslContext): HttpClient =
    HttpClient.create()
      .secure { it.sslContext(sslContext) }
      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20000)
      .responseTimeout(Duration.ofMillis(20000))
      .doOnConnected { conn: Connection ->
        conn
          .addHandlerLast(ReadTimeoutHandler(20000, TimeUnit.MILLISECONDS))
          .addHandlerLast(WriteTimeoutHandler(20000, TimeUnit.MILLISECONDS))
      }

  @Bean
  fun sslContext(): SslContext =
    SslContextBuilder
      .forClient()
      .sessionTimeout(20000)
      .trustManager(InsecureTrustManagerFactory.INSTANCE)
      .build()

}