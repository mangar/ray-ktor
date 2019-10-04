package com.rw.webapp

import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlin.text.get


const val HOME = "/"

fun Route.home() {
  get(HOME) {
    call.respond(FreeMarkerContent("home.ftl", null))
  }
}

