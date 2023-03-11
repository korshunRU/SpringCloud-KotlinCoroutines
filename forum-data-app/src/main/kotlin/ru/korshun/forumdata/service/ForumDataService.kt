package ru.korshun.forumdata.service

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import ru.korshun.commons.model.dto.forumData.Albums
import ru.korshun.commons.model.dto.forumData.Post
import ru.korshun.commons.model.dto.forumData.Todos
import ru.korshun.commons.model.response.dto.ForumData
import ru.korshun.forumdata.logger

@Service
class ForumDataService {

  @Autowired
  private lateinit var webClient: WebClient

  @Value("\${integration.url.posts}")
  private lateinit var postsUrl: String

  @Value("\${integration.url.todos}")
  private lateinit var todosUrl: String

  @Value("\${integration.url.albums}")
  private lateinit var albumsUrl: String

  suspend fun getData(id: Long): ForumData = supervisorScope {
    val def = listOf(
      async { getPosts(id) },
      async { getTodos(id) },
      async { getAlbums(id) }
    ).awaitAll()

    @Suppress("UNCHECKED_CAST")
    ForumData(
      posts = def[0] as List<Post>,
      todos = def[1] as List<Todos>,
      albums = def[2] as List<Albums>
    )
  }

  private suspend fun getPosts(id: Long): List<Post> {
    logger.info { "getPosts start, id: $id" }
    val res = webClient
      .get()
      .uri { builder -> builder.path(String.format(postsUrl, id)).build() }
      .retrieve()
      .awaitBody<List<Post>>()
    logger.info { "getPosts finish, id: $id" }
    return res
  }

  private suspend fun getTodos(id: Long): List<Todos> {
    logger.info { "getTodos start, id: $id" }
    val res =  webClient
      .get()
      .uri { builder -> builder.path(String.format(todosUrl, id)).build() }
      .retrieve()
      .awaitBody<List<Todos>>()
    logger.info { "getTodos finish, id: $id" }
    return res
  }

  private suspend fun getAlbums(id: Long): List<Albums> {
    logger.info { "getAlbums start, id: $id" }
    val res =  webClient
      .get()
      .uri { builder -> builder.path(String.format(albumsUrl, id)).build() }
      .retrieve()
      .awaitBody<List<Albums>>()
    logger.info { "getAlbums finish, id: $id" }
    return res
  }

}