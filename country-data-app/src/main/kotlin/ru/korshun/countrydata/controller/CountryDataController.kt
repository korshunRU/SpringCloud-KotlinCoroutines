package ru.korshun.countrydata.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.korshun.commons.model.dto.countryData.Country
import ru.korshun.countrydata.service.CountryDataService

@RestController
@RequestMapping("/country-data/api/v1")
class CountryDataController {

  @Autowired
  private lateinit var service: CountryDataService

  @GetMapping("/country/{name}")
  suspend fun getCountryData(@PathVariable("name") name: String): Country =
    service.getData(name)

}