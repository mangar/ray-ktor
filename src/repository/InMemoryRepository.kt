package com.rw.repository

import com.rw.*
import com.rw.model.*
import java.lang.IllegalArgumentException
import java.util.concurrent.atomic.*

class InMemoryRepository : Repository {

  private val idCounter = AtomicInteger()
  private val phrases = ArrayList<EmojiPhrase>()


  override suspend fun add(phrase: EmojiPhrase): EmojiPhrase {
    if (phrases.contains(phrase)) {
      return phrases.find{ it == phrase }!!
    }
    phrase.id = idCounter.incrementAndGet()
    phrases.add(phrase)
    return phrase
  }

  override suspend fun phrase(id: Int): EmojiPhrase? = phrase(id.toString())

  override suspend fun phrase(id: String): EmojiPhrase? {
//    phrases.find { it.id.toString() == id } ? throw IllegalArgumentException("No phrase found for $id")
    return phrases.find { it.id.toString() == id }
  }

  override suspend fun phrases(): ArrayList<EmojiPhrase> = phrases

  override suspend fun remove(phrase: EmojiPhrase) {
    if (!phrases.contains(phrase)) {
      throw IllegalArgumentException("No phrase found for id: ${phrase.id}")
    }
    phrases.remove(phrase)
  }

  override suspend fun remove(id: Int): Boolean = phrases.remove(phrase(id))

  override suspend fun remove(id: String): Boolean = phrases.remove(phrase(id))

  override suspend fun clear()  = phrases.clear()


}
