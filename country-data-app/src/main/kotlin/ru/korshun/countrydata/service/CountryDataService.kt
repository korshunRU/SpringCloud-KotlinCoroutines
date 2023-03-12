package ru.korshun.countrydata.service

import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import ru.korshun.commons.model.dto.countryData.Country
import ru.korshun.commons.model.exception.CountryException
import ru.korshun.commons.model.response.dto.CountryData
import ru.korshun.commons.model.util.getAsync
import ru.korshun.commons.model.util.logger
import ru.korshun.commons.model.util.wait

@Service
class CountryDataService {

  @Autowired
  private lateinit var webClient: WebClient

  @Value("\${integration.url.country}")
  private lateinit var countryUrl: String

  private val wrongCountries = arrayOf(
    "Norway",
    "Finland",
    "Mexico",
    "Turkey",
    "Brazil"
  )

  suspend fun getData(country: String): Country {
    logger.info { "getCountry start, name: $country" }

    val res = supervisorScope {
      val nameD = async { getName(country) }
      val capitalD = async { getCapital(country) }
      val languagesD = async { getLanguages(country) }

      Country(
        name = nameD.wait { Country.NameInfo() },
        capital = capitalD.wait { null },
        languages = languagesD.wait { null }
      )
    }

    logger.info { "getCountry finish, name: $country" }
    return res
  }

  private suspend fun getName(country: String): Country.NameInfo? {
    logger.info { "getName start, name: $country" }
    val res = webClient.getAsync<CountryData>(String.format(countryUrl, country))

    // for checking error query in the supervisorScope
    if (wrongCountries.contains(country)) throw CountryException("Wrong country: $country")

    logger.info { "getName finish, name: $country" }
    return res[0].name
  }

  private suspend fun getCapital(country: String): Array<String>? {
    logger.info { "getCapital start, name: $country" }
    val res = webClient.getAsync<CountryData>(String.format(countryUrl, country))
    logger.info { "getCapital finish, name: $country" }
    return res[0].capital
  }

  private suspend fun getLanguages(country: String): Map<String, String>? {
    logger.info { "getLanguages start, name: $country" }
    val res = webClient.getAsync<CountryData>(String.format(countryUrl, country))
    logger.info { "getLanguages finish, name: $country" }
    return res[0].languages
  }

}