package com.rw.webapp

import com.rw.*
import com.rw.model.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.freemarker.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlin.text.get


const val PHRASES = "/phrases"


fun Route.phrases(db: Repository) {
  authenticate("auth") {
    get(PHRASES) {
      val user = call.authentication.principal as User
      val phrases = db.phrases()
      call.respond(FreeMarkerContent("phrases.ftl",
        mapOf("phrases" to phrases,
        "userName" to user.displayName))
      )
    }
  }

}

