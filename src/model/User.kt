package com.rw.model

import io.ktor.auth.*

data class User(val displayName: String): Principal
