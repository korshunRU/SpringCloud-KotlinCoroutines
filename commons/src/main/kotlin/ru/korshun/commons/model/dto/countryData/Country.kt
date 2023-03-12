package ru.korshun.commons.model.dto.countryData

class Country(
  val name: NameInfo? = null,
  val capital: Array<String>? = null,
  val languages: Map<String, String>? = null
) {

  class NameInfo(
    val common: String? = null,
    val official: String? = null
  )

}

