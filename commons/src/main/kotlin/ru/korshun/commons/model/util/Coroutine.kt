package ru.korshun.commons.model.util

import kotlinx.coroutines.Deferred
import ru.korshun.commons.model.exception.BaseException

suspend inline fun <reified T> Deferred<T>.wait(rs: () -> T?): T? =
  try {
    this.await()
  } catch (e: BaseException) {
    rs.invoke()
  }