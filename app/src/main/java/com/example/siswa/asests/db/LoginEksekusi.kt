package com.example.siswa.asests.db

import Classes
import DataUSer
import LoginCompare
import LoginRequest
import LoginResponse
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import io.github.jan.supabase.postgrest.from
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.json.JSONObject
import java.util.Base64

suspend fun login(username: String, password: String): String? {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    val url = "https://mimath-auth.vercel.app/api/auth/login" // Gunakan constant

    return try {
        val loginRequest = LoginRequest(username, password)
        val response: LoginResponse = client.post(url) {
            headers {
                append(HttpHeaders.ContentType, "application/json")
            }
            setBody(loginRequest) // Menggunakan setBody
        }.body()

        Log.d("response.token", response.token)
        response.token
    } catch (e: Exception) {
        Log.e("LoginError", "Login failed: ${e.message}", e)
        null
    } finally {
        client.close()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun compareId(token: String): LoginCompare? {
    return try {
        val jwtParts = token.split(".")

        val payload = String(Base64.getDecoder().decode(jwtParts[1]))

        // Menguraikan payload sebagai JSON
        val json = JSONObject(payload)

        // Mengambil nilai userId dari payload JWT
        val userId = json.getInt("user_id")
        Log.d("uid", "userId: ${LoginCompare(userId)}")

        // Mengembalikan hasil sebagai data class LoginCompare
        LoginCompare(userId)
    } catch (e: Exception) {
        Log.e("JWTError", "Error parsing token: ${e.message}", e)
        null
    }
}


val json = Json {
    ignoreUnknownKeys = true // Mengabaikan properti yang tidak dikenal
}

suspend fun getUserById(loginCompare: LoginCompare): List<DataUSer>? {
    val loginResult = loginCompare.userId
    val fetchAll = UsersViewModel().supabase.from("users").select {
        filter {
            eq("id", loginResult)
        }
    }

    return try {
        // Dekode hasil fetchAll.data menjadi List<DataUSer> secara langsung
        val usersList = json.decodeFromString<List<DataUSer>>(fetchAll.data)
        Log.d("jaybay", "Hasil data: $usersList")
        usersList // Kembalikan daftar DataUSer
    } catch (e: Exception) {
        Log.e("jaybay", "Error : ${e.message}", e)
        null
    }
}

//suspend fun getClasess(){
//   val addClass =  UsersViewModel().supabase.from("classes").select().decodeSingle<Classes>()
//
//    val name = addClass.name
//    val description = addClass.description
//    val link_meet = addClass.link_meet
//    val link_youtobe = addClass.link_youtobe
//    val class_code = addClass.class_code
//    val created_by = addClass.created_by
//    val is_hidden = addClass.is_hidden
//}