package com.example.siswa.asests.pages

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siswa.R
import com.example.siswa.asests.component.element.ImageLogo
import com.example.siswa.asests.component.layout.LoginLayout
import com.example.siswa.ui.theme.SiswaTheme
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginPage(navController: NavController){

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var backPressedOnce by remember { mutableStateOf(false) }

    BackHandler {
        if (backPressedOnce) {
            (context as? Activity)?.finish()
        } else {
            // Tampilkan toast dan set flag bahwa tombol back sudah ditekan
            backPressedOnce = true
            Toast.makeText(context, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()

            // Reset flag setelah beberapa detik jika tidak ada penekanan kedua
            coroutineScope.launch {
                delay(2000L) // 2 detik
                backPressedOnce = false
            }
        }
    }
    LoginLayout(navController)
}


@Preview(showBackground = true)
@Composable
fun PreviewImage() {
    val navController = rememberNavController()
    SiswaTheme {
        LoginPage(navController)
    }
}