package ru.korshun.commons.model.dto.forumData

import ru.korshun.commons.model.dto.forumData.api.BaseDto

class Todos(
  id: Long,
  userId: Long,
  title: String,
  val completed: Boolean
): BaseDto(id, userId, title)