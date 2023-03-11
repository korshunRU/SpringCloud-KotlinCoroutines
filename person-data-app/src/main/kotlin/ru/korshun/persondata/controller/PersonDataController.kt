package ru.korshun.persondata.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.korshun.commons.model.response.api.BaseResponse
import ru.korshun.commons.model.response.dto.PersonData
import ru.korshun.persondata.service.PersonDataService

@RestController
@RequestMapping("/person-data/api/v1")
class PersonDataController {

  @Autowired
  private lateinit var service: PersonDataService

  @GetMapping("/person")
  suspend fun getUserData(): BaseResponse<PersonData> =
    BaseResponse(service.getData())

}