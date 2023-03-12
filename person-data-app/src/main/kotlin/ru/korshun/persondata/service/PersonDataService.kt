package ru.korshun.persondata.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import ru.korshun.commons.model.dto.countryData.Country
import ru.korshun.commons.model.dto.personData.Person
import ru.korshun.commons.model.response.dto.PersonData
import ru.korshun.commons.model.util.getAsync
import ru.korshun.commons.model.util.logger


@Service
class PersonDataService {

  @Autowired
  @Qualifier("personWebClient")
  private lateinit var personWebClient: WebClient

  @Autowired
  @Qualifier("countryWebClient")
  private lateinit var countryWebClient: WebClient.Builder

  @Value("\${integration.url.person}")
  private lateinit var personUrl: String

  @Value("\${integration.local.country-app}")
  private lateinit var countryBaseUrl: String

  @Value("\${integration.local.url.country}")
  private lateinit var countryUrl: String

  suspend fun getData(): PersonData =
    getPerson().apply {
      person.location.countryInfo = getCountry(person.location.country)
    }

  private suspend fun getPerson(): PersonData {
    logger.info { "getPerson start" }
    val res = personWebClient.getAsync<Person>(personUrl)
    logger.info { "getPerson finish" }
    return PersonData(res.results[0])
  }

  private suspend fun getCountry(country: String): Country {
    logger.info { "getCountry start, country: $country" }
    val res = countryWebClient
      .baseUrl(countryBaseUrl)
      .build()
      .getAsync<Country>(String.format(countryUrl, country))
    logger.info { "getCountry finish, country: $country" }
    return res
  }

}