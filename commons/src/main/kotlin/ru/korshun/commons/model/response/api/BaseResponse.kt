package ru.korshun.commons.model.response.api

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class BaseResponse<T>(
  val result: T? = null,
  val error: String? = null
)