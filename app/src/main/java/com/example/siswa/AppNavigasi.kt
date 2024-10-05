package com.example.siswa

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.siswa.asests.component.layout.LoadingScreen
import com.example.siswa.asests.db.validateToken
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
import getToken
import kotlinx.coroutines.launch

@Composable
fun AppNavigasi() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var isTokenValid by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) } // Loading state

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val token = getToken(context)
            Log.d("AppNavigasijay", "Token from SharedPreferences: ${token.toString()}")// Get the token from SharedPreferences
            if (token != null) {
                isTokenValid = validateToken(token)
                Log.d("isTokenValid", isTokenValid.toString())
            }
            isLoading = false
        }
    }

    // Display a loading screen while checking the token
    if (isLoading) {
        // Replace this with your actual loading screen
        LoadingScreen()
    } else {
        // Navigate to the appropriate screen based on token validation
        NavHost(navController = navController, startDestination = if (isTokenValid) "${Routes.dashboard}/{fromProfile}" else Routes.login) {
            composable(Routes.login) {
                LoginPage(navController)
            }
            composable(Routes.dashboard + "/{fromProfile}") { navBackStackEntry ->
                val fromProfile = navBackStackEntry.arguments?.getBoolean("fromProfile") ?: false
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

            composable(
                route = Routes.kelas + "?id={id}&desc={desc}&link_meet={link_meet}&created_by={created_by}&link_youtube={link_youtube}",
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType },
                    navArgument("desc") { type = NavType.StringType },
                    navArgument("link_meet") { type = NavType.StringType },
                    navArgument("created_by") { type = NavType.IntType },
                    navArgument("link_youtube") { type = NavType.StringType; nullable = true } // Karena nullable
                )
            ) { backStackEntry ->
                Kelas(
                    navController = navController,
                    id = backStackEntry.arguments?.getInt("id") ?: 0,
                    desc = backStackEntry.arguments?.getString("desc") ?: "",
                    linkMeet = backStackEntry.arguments?.getString("link_meet") ?: "",
                    createdBy = backStackEntry.arguments?.getInt("created_by") ?: 0,
                    link_youtube = backStackEntry.arguments?.getString("link_youtube")
                )
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
        }
    }
}

