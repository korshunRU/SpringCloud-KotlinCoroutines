package ru.korshun.commons.model.dto.forumData

import ru.korshun.commons.model.dto.forumData.api.BaseDto

class Albums(
  id: Long,
  userId: Long,
  title: String
): BaseDto(id, userId, title)