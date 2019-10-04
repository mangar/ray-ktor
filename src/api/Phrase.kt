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

const val PHRASES_INITIAL_LOAD_ENDPOINT = "$API_VERSION/phrases/initialLoad"

fun Route.phrasesAPIGET(db: Repository) {
  get(PHRASES_ENDPOINT) {
    val phrases = db.phrases()
    call.respond(phrases.toArray())
  }
}

fun Route.phraseAPIPOST(db: Repository) {
  post(PHRASE_ENDPOINT) {
    val request = call.receive<Request>()
    val phrase = db.add(EmojiPhrase(request.emoji, request.phrase))
    call.respond(phrase)
  }
}

fun Route.phrasesInitialLoadAPIGET(db: Repository) {
  post(PHRASES_INITIAL_LOAD_ENDPOINT) {
    //
    db.add(EmojiPhrase("⛱☕️\uD83C\uDFD0\uD83C\uDF7A\uD83E\uDD43\uD83C\uDFD6", "vacation"))
    db.add(EmojiPhrase("\uD83D\uDC4D\uD83D\uDC4C\uD83E\uDD1F", "okidoki"))
    db.add(EmojiPhrase("\uD83D\uDC4D", "ok"))

    //
    val phrases = db.phrases()
    call.respond(phrases.toArray())
  }
}



