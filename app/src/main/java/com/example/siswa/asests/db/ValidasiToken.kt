package com.example.siswa.asests.db

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json

@OptIn(InternalAPI::class)
suspend fun validateToken(token: String): Boolean {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    val url = "https://mimath-auth.vercel.app/api/auth/validate" // Gunakan constant

    return try {
        val response = client.post(url) {
            headers {
                append(HttpHeaders.ContentType, "application/json")
                append(HttpHeaders.Authorization, "Bearer $token")
            }
        }
        Log.d("is token valid", "validateToken: ${response.status.value == 200}")

        response.status.value == 200
    } catch (e: Exception) {
        Log.e("LoginError", "Login failed: ${e.message}", e)
        false
    } finally {
        client.close()
    }
}
