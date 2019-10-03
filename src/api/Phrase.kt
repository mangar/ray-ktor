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

fun Route.phrasesGET(db: Repository) {
  get(PHRASES_ENDPOINT) {
    val phrases = db.phrases()
    call.respond(phrases.toArray())
  }
}

fun Route.phrasePOST(db: Repository) {
  post(PHRASE_ENDPOINT) {
    val request = call.receive<Request>()
    val phrase = db.add(EmojiPhrase(request.emoji, request.phrase))
    call.respond(phrase)
  }
}




