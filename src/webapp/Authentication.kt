package com.rw.webapp

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

const val AUTHENTICATION = "/auth"


fun Route.authentication() {
  get(AUTHENTICATION) {
    call.respondText("Authentication...")
  }
}


const val API_VERSION = "/api/v1"
