import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.example.siswa.asests.db.UsersViewModel
import com.example.siswa.ui.theme.Routes
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

fun validasiLogin(username: String, password: String, navigateTo: (String) -> Unit, context: Context) {
    val usersViewModel = UsersViewModel()
    val fromProfile = false

    usersViewModel.viewModelScope.launch {
        // Panggil API login untuk mendapatkan token
        val token = login(username, password)

        try {
            if (token != null) {
                // Jika token valid, lanjutkan untuk menyimpan token dan navigasi
                saveToken(token, context)

                // Ambil token yang tersimpan untuk verifikasi
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
    }
}



fun saveToken(token: String, context: Context) {
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("token", token)
        apply() // Simpan secara asinkron
    }
}

fun getToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("token", null) // Mengembalikan null jika token tidak ditemukan
}


