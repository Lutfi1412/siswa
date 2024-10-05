package com.example.siswa

import ImgUrl
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Atur warna status bar berdasarkan tema
            ChangeStatusBarColor()
            Column(
                modifier = Modifier
                    .systemBarsPadding()
                    .fillMaxSize()
            ) {
                AppNavigasi()
            }

        }
    }

    @Composable
    fun ChangeStatusBarColor() {
        // Deteksi apakah tema gelap diaktifkan
        val isDarkTheme = isSystemInDarkTheme()

        // Ubah warna status bar sesuai tema
        val statusBarColor = if (isDarkTheme) {
            androidx.compose.ui.graphics.Color.Black
        } else {
            androidx.compose.ui.graphics.Color.White
        }

        // Set status bar color (harus di luar Composable)
        window.statusBarColor = statusBarColor.toArgb()

        // Jika API level mendukung (API 23+), atur warna teks status bar (icon dan teks di status bar)
        if (!isDarkTheme) {
            // Teks status bar hitam untuk background status bar terang
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility or android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            // Teks status bar putih untuk background status bar gelap
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility and android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }
        private fun enableEdgeToEdge() {
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility or android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE or android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}

