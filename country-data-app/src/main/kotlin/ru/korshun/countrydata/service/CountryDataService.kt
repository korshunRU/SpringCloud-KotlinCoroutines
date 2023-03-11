package ru.korshun.countrydata.service

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import ru.korshun.commons.model.dto.countryData.Country
import ru.korshun.commons.model.response.dto.CountryData
import ru.korshun.countrydata.logger

@Service
class CountryDataService {

  @Autowired
  private lateinit var webClient: WebClient

  @Value("\${integration.url.country}")
  private lateinit var countryUrl: String

  suspend fun getData(country: String): Country = supervisorScope {
    logger.info { "getCountry start, name: $country" }
    val rsDef = listOf(
      async { getName(country) },
      async { getCapital(country) },
      async { getLanguages(country) }
    ).awaitAll()

    @Suppress("UNCHECKED_CAST")
    val res = Country(
      name = rsDef[0] as Country.NameInfo,
      capital = rsDef[1] as Array<String>,
      languages = rsDef[2] as Map<String, String>
    )
    logger.info { "getCountry finish, name: $country" }
    return@supervisorScope res
  }

  private suspend fun getName(country: String): Country.NameInfo {
    logger.info { "getName start, name: $country" }
    val res = webClient
      .get()
      .uri { builder -> builder.path(String.format(countryUrl, country)).build() }
      .retrieve()
      .awaitBody<CountryData>()
    logger.info { "getName finish, name: $country" }
    return res[0].name
  }

  private suspend fun getCapital(country: String): Array<String> {
    logger.info { "getCapital start, name: $country" }
    val res = webClient
      .get()
      .uri { builder -> builder.path(String.format(countryUrl, country)).build() }
      .retrieve()
      .awaitBody<CountryData>()
    logger.info { "getCapital finish, name: $country" }
    return res[0].capital
  }

  private suspend fun getLanguages(country: String): Map<String, String> {
    logger.info { "getLanguages start, name: $country" }
    val res = webClient
      .get()
      .uri { builder -> builder.path(String.format(countryUrl, country)).build() }
      .retrieve()
      .awaitBody<CountryData>()
    logger.info { "getLanguages finish, name: $country" }
    return res[0].languages
  }

}