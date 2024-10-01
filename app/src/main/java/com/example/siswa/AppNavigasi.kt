package com.example.siswa

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.siswa.asests.pages.Addclass
import com.example.siswa.asests.pages.Dashboard
import com.example.siswa.asests.pages.DetailQuiz
import com.example.siswa.asests.pages.Hasil
import com.example.siswa.asests.pages.Instruksi
import com.example.siswa.asests.pages.Kelas
import com.example.siswa.asests.pages.LoginPage
import com.example.siswa.asests.pages.Quiz
import com.example.siswa.asests.pages.Tentang
import com.example.siswa.asests.pages.UbahPassword
import com.example.siswa.ui.theme.Routes

@Composable
fun AppNavigasi(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.login, builder = {
        composable(Routes.login) {
            LoginPage(navController)
        }
        composable(Routes.dashboard + "/{fromProfile}") { navBackStackEntry ->
            val fromProfile = navBackStackEntry.arguments?.getBoolean("fromProfile")?: false
            Dashboard(navController = navController, fromProfile = fromProfile)
        }
        composable(Routes.addclass) {
            Addclass(navController)
        }
        composable(Routes.detailquiz) {
            DetailQuiz(navController)
        }
        composable(Routes.instruksi) {
            Instruksi(navController)
        }
        composable(Routes.kelas) {
            Kelas(navController)
        }
        composable(Routes.quiz) {
            Quiz(navController)
        }
        composable(Routes.tentang) {
            Tentang(navController)
        }
        composable(Routes.ubahpassword) {
            UbahPassword(navController)
        }
        composable(Routes.hasil) {
            Hasil(navController)
        }
    })
}