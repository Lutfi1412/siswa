package com.example.siswa.asests.pages

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.siswa.R
import com.example.siswa.asests.component.layout.KelasLayout
import com.example.siswa.ui.theme.Routes

@Composable
fun Kelas(
    navController: NavController,
    id: Int,
    desc: String,
    linkMeet: String,
    createdBy: Int,
    link_youtube: String?){

    val fromProfile = false

    BackHandler {
        navController.navigate(Routes.dashboard+"/"+fromProfile) {
            popUpTo(Routes.dashboard+"/"+fromProfile) { inclusive = true } // Menghapus stack sebelumnya
        }
    }

    KelasLayout(
        items = remember { List(10) { "Item $it" } },
        painter =  painterResource(id = R.drawable.anime),
        id = id,
        desc = desc,
        linkMeet = linkMeet,
        createdBy = createdBy,
        link_youtube = link_youtube,
        painter2 = painterResource(id = R.drawable.anime),
        navController = navController
    )
}