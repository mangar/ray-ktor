package com.rw.api

import com.rw.*
import com.rw.model.*
import com.rw.webapp.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


const val PHRASE_ENDPOINT = "$API_VERSION/phrase"

const val PHRASES_ENDPOINT = "$API_VERSION/phrases"

//
//
fun Route.phraseGET() {
  get(PHRASE_ENDPOINT) {
    call.respondText("$PHRASE_ENDPOINT")
  }
}

//
//
fun Route.phrasePOST(db: Repository) {
  post(PHRASE_ENDPOINT) {
    val request = call.receive<Request>()
    val phrase = db.add(EmojiPhrase(request.emoji, request.phrase))
    call.respond(phrase)
  }
}


//
//
fun Route.phrasesGET() {
  get(PHRASES_ENDPOINT) {
    call.respondText("$PHRASES_ENDPOINT")
  }
}





