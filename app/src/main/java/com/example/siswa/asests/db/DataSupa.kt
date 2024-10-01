import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.http.HttpHeaders // Import statement yang ditambahkan
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class LoginRequest(val username: String, val password: String)

@Serializable
data class LoginResponse(val token: String)

@OptIn(InternalAPI::class)
suspend fun login(username: String, password: String): String? {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    return try {
        val loginRequest = LoginRequest(username,password)
        val response: LoginResponse = client.post("https://mimath-auth.vercel.app/api/auth/login") {
            headers {
                append(HttpHeaders.ContentType, "application/json")
            }
            body = loginRequest
        }.body() // Ambil body sebagai LoginResponse

        response.token // Ambil token dari respons
    } catch (e: Exception) {
        Log.e("LoginError", "Login failed: ${e.message}", e)
        null
    } finally {
        client.close()
    }
}