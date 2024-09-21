package com.tejasdev.kmp_cmp.apiClient

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.logging.Logger
import kotlinx.serialization.json.Json
import org.jetbrains.skiko.SkikoLoggerInterface
import org.jetbrains.skiko.setupSkikoLoggerFactory

val httpClient = HttpClient{
    install(ContentNegotiation){
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        })
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
        filter { request ->
            request.url.host.contains("ktor.io")
        }
        sanitizeHeader { header -> header == HttpHeaders.Authorization }
    }
}