package com.rw.webapp

import com.rw.*
import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlin.text.get


const val PHRASES = "/phrases"


fun Route.phrases(db: Repository) {
  get(PHRASES) {
    val phrases = db.phrases()
    call.respond(FreeMarkerContent("phrases.ftl", mapOf("phrases" to phrases)))
  }

}

