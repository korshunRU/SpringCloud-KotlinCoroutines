package ru.korshun.commons.model.dto.forumData

import ru.korshun.commons.model.dto.forumData.api.BaseDto

class Post(
  id: Long,
  userId: Long,
  title: String,
  val body: String
): BaseDto(id, userId, title)