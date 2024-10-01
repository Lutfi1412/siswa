package com.example.siswa.asests.component.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siswa.asests.component.fragment.addclass.AddClass
import com.example.siswa.asests.component.fragment.BackBtn
import com.example.siswa.ui.theme.Routes
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun AddLayout(navController:NavController){
    val fromProfile = false

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackBtn(text = "Bergabung Dengan Kelas", modifier = Modifier
            .fillMaxWidth(0.7f)
            .padding(vertical = 5.dp), onclik = { navController.navigate("${Routes.dashboard}/${fromProfile}")})
        AddClass(navController, modifier = Modifier.padding(horizontal = 20.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewAdd() {
    val navController = rememberNavController()
    SiswaTheme {
        AddLayout(navController)
    }
}