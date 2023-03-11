package ru.korshun.countrydata

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.junit.jupiter.api.Test
import org.springframework.util.ResourceUtils
import ru.korshun.commons.model.response.dto.CountryData

class DtoTest {

  private val objectMapper: ObjectMapper =
    ObjectMapper()
      .registerModule(kotlinModule())
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  private fun <T> getJson(name: String, clazz: Class<T>) =
    objectMapper.readValue(ResourceUtils.getFile(String.format("classpath:json/%s", name)), clazz)

  @Test
  fun dtoTest() {
    val country = getJson("country.json", CountryData::class.java)
    assert(country != null)
    assert(country[0].name.common == "Ukraine")
  }

}