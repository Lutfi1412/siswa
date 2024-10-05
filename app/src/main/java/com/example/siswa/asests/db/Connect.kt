package com.example.siswa.asests.db

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {

    private val supabaseUrl = "https://ijerrslgidofqvsfecgx.supabase.co"
    val supabaseKey =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImlqZXJyc2xnaWRvZnF2c2ZlY2d4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY2NzczNzcsImV4cCI6MjA0MjI1MzM3N30.IPXmAJeqX_0QqgOrbgz3yoI7-2zYH0C4vyDkqX5VaiQ"


    val supabase = createSupabaseClient(
        supabaseUrl = supabaseUrl,
        supabaseKey = supabaseKey
    ) {
        install(Postgrest)
    }
}

//var users: List<LoginResponse> = listOf()
////        private set
//
//    fun fetchUsers() {
//        viewModelScope.launch {
//            try {
//                // Fetch users from Supabase
//                val response = supabase.from("users").select().decodeList<LoginResponse>()
//                users = response
//                Log.d("UsersViewModel", "Users: $users")
//            } catch (e: Exception) {
//                Log.e("UsersViewModel", "Error fetching users: ${e.message}")
//            }
//        }
//    }



//    fun reqLogin(){
//        val client = OkHttpClient()
//        val mediaType = "text/plain".toMediaType()
//        val body = "".toRequestBody(mediaType)
//        val request = Request.Builder()
//            .url("https://mimath-auth.vercel.app/api/auth/validate")
//            .post(body)
//            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo0MCwidXNlcm5hbWUiOiJzc25zIiwicm9sZSI6InRlYWNoZXIiLCJpYXQiOjE3Mjc3MDc4NDIsImV4cCI6MTcyNzcwNzkwMn0.U7cracgLbL7--HzRawnnSGdjgKXPBw-9Bzot7ORF1bs")
//            .build()
//
//        val response = client.newCall(request).execute()
//
//        response.code == 200
//    }