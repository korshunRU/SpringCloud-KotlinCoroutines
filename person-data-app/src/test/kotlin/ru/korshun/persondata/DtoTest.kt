package ru.korshun.persondata

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.junit.jupiter.api.Test
import org.springframework.util.ResourceUtils
import ru.korshun.commons.model.dto.personData.Person

class DtoTest {

  private val objectMapper: ObjectMapper =
    ObjectMapper()
      .registerModule(kotlinModule())
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  private fun <T> getJson(name: String, clazz: Class<T>) =
    objectMapper.readValue(ResourceUtils.getFile(String.format("classpath:json/%s", name)), clazz)

  @Test
  fun dtoTest() {
    val person = getJson("person.json", Person::class.java)
    assert(person != null)
    assert(person.results[0].name.first == "Luisa")
  }

}