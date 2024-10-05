@file:Suppress("NAME_SHADOWING")

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import com.example.siswa.asests.db.UsersViewModel
import com.example.siswa.asests.db.compareId
import com.example.siswa.asests.db.getUserById
import com.example.siswa.asests.db.login
import com.example.siswa.ui.theme.Routes
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
fun validasiLogin(username: String, password: String, navigateTo: (String) -> Unit, context: Context) {
    val usersViewModel = UsersViewModel()
    val fromProfile = false


    usersViewModel.viewModelScope.launch {
        val token = login(username, password)
        try {
            if (token != null) {
                saveToken(token, context)
                compareId(token)
                getUserById(LoginCompare(userId = compareId(token)?.userId ?: 0))
                val savedToken = getToken(context)
                Log.d("TokenVerification", "Token yang disimpan: $savedToken")
                navigateTo(Routes.dashboard + "/" + fromProfile)
            } else {
                // Jika token null, artinya login gagal
                Toast.makeText(context, "Username atau Password salah", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e("UsersViewModel", "Error fetching users: ${e.message}")
            Toast.makeText(context, "Terjadi kesalahan, silakan coba lagi", Toast.LENGTH_SHORT).show()
        }
        val users = getUserById(LoginCompare(userId = token?.let { compareId(it)?.userId } ?: 0))
        val nama = users?.firstOrNull()?.name ?: "Nama tidak ditemukan"
        val username = users?.firstOrNull()?.username ?: "Username tidak ditemukan"
        val id = users?.firstOrNull()?.id ?: -1
        Log.d("coba", "Nama user = $nama")
        saveDataUser(nama, username, id, context)
        Log.d("coba", "Id = ${getDataId(context)}")
    }
}
fun saveToken(token: String, context: Context) {
    Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("token", token)
        apply() // Pastikan memanggil apply() atau commit() untuk menyimpan perubahan
    }
}


fun getToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("token", null) // Mengembalikan null jika token tidak ditemukan
}


fun getDataNama(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("name", null) // Ambil data nama
}

fun getDataUsername(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("username", null) // Ambil data username
}

fun saveDataUser(name: String, username: String, id: Int, context: Context) {
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("name", name)
        putString("username", username)
        putInt("id", id)
        apply()
        Log.d("saveDataUser", "Saved name: $name, username: $username, id: $id") // Log saat menyimpan
    }
}

fun getDataId(context: Context): Int {
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    val id = sharedPreferences.getInt("id", -1)
    Log.d("getDataId", "Retrieved id: $id") // Log saat mengambil
    return id
}




