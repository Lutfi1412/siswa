package com.example.siswa.asests.pages

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.siswa.asests.component.layout.HasilLayout
import com.example.siswa.ui.theme.Routes

@Composable
fun Hasil(navController: NavController){
    BackHandler {
        navController.navigate(Routes.kelas) {
            popUpTo(Routes.kelas) { inclusive = true } // Menghapus stack sebelumnya
        }
    }
    HasilLayout(feedback = "hajshjahsj", nilai = "90", navController = navController)
}