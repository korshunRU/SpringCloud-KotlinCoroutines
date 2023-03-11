package ru.korshun.commons.model.response.dto

import ru.korshun.commons.model.dto.forumData.Albums
import ru.korshun.commons.model.dto.forumData.Post
import ru.korshun.commons.model.dto.forumData.Todos

class ForumData(
  val posts: List<Post> = emptyList(),
  val todos: List<Todos> = emptyList(),
  val albums: List<Albums> = emptyList()
)