package ru.korshun.forumdata.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.korshun.commons.model.response.api.BaseResponse
import ru.korshun.commons.model.response.dto.ForumData
import ru.korshun.forumdata.service.ForumDataService

@RestController
@RequestMapping("/forum-data/api/v1")
class ForumDataController {

  @Autowired
  private lateinit var service: ForumDataService

  @GetMapping("/user/{id}")
  suspend fun getUserData(@PathVariable("id") id: Long): BaseResponse<ForumData> =
    BaseResponse(service.getData(id))

}