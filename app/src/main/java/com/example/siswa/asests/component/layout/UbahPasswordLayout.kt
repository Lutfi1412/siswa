package com.example.siswa.asests.component.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.siswa.asests.component.fragment.BackBtn
import com.example.siswa.asests.component.fragment.sandi.UbahSandi
import com.example.siswa.ui.theme.Routes
import com.example.siswa.ui.theme.SiswaTheme

@Composable

fun UbahPasswordLayout(navController: NavController, fromProfile: Boolean = false){

    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        BackBtn(text = "Ubah Kata Sandi", modifier = Modifier.padding(vertical = 5.dp), onclik = { navController.navigate("${Routes.dashboard}/$fromProfile") })
        UbahSandi(navController, modifier = Modifier.padding(horizontal = 20.dp))
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewUbahPassword() {
//    SiswaTheme {
//        UbahPasswordLayout()
//    }
//}