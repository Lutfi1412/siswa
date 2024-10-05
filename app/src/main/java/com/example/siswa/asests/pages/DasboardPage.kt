package com.example.siswa.asests.pages

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siswa.asests.component.fragment.FooterBtn
import com.example.siswa.asests.component.layout.ProfileLayout
import com.example.siswa.ui.theme.SiswaTheme

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.siswa.asests.component.layout.HomeLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Dashboard(navController: NavController, fromProfile: Boolean = false) {
    val homeVisible = remember { mutableStateOf(!fromProfile) }
    val context = LocalContext.current
    var backPressedOnce by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    // BackHandler untuk menangani tombol back
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

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (homeVisible.value) {
            HomeLayout(modifier = Modifier.weight(1f), navController = navController)
        } else {
            ProfileLayout(navController)
        }
        FooterBtn(homeVisible = homeVisible)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDash() {
    val navController = rememberNavController()
    SiswaTheme {
        Dashboard(navController)
    }
}