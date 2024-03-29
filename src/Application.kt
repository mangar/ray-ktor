package com.rw

import com.rw.api.*
import com.rw.repository.*
import com.rw.webapp.*
import com.ryanharter.ktor.moshi.*
import freemarker.cache.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(DefaultHeaders)

    install(StatusPages) {
      exception<Throwable> { e ->
        call.respondText(e.localizedMessage, ContentType.Text.Plain, HttpStatusCode.InternalServerError)
      }
    }

    install(ContentNegotiation) {
      moshi()
    }

    install(FreeMarker) {
      templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }


    val db = InMemoryRepository()

    routing {
      home()
      authentication()
      about()
      phrases(db)

      phrasesInitialLoadAPIGET(db)
      phrasesAPIGET(db)
      phraseAPIPOST(db)
    }
}

