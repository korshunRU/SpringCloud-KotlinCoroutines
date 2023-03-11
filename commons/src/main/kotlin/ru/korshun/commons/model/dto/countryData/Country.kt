package ru.korshun.commons.model.dto.countryData

class Country(
  val name: NameInfo,
  val capital: Array<String>,
  val languages: Map<String, String>
) {

  class NameInfo(
    val common: String,
    val official: String
  )

}

