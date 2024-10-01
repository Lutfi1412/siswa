package com.example.siswa.asests.component.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.siswa.R
import com.example.siswa.asests.component.element.TextC
import com.example.siswa.asests.component.element.TextD
import com.example.siswa.asests.component.fragment.BackBtn
import com.example.siswa.asests.component.fragment.home.CardQuizGuru
import com.example.siswa.asests.component.fragment.kelas.BtnGuru
import com.example.siswa.asests.component.fragment.kelas.ProfileGuru
import com.example.siswa.ui.theme.Routes
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun KelasLayout(
    items : List<String>,
    painter: Painter,
    nama : String,
    gelar : String,
    detail : String,
    painter2: Painter,
    navController: NavController
){
    val fromProfile = false
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 5.dp)
    ) {
        BackBtn(text = "Course Detail", modifier = Modifier, onclik = { navController.navigate("${Routes.dashboard}/${fromProfile}")})
        LazyColumn(
            modifier = Modifier.fillMaxHeight().padding(horizontal = 20.dp),
        ) {
            item {
                TextD(textRes = "Course Detail", size = 22, modifier = Modifier)
            }
            item {
                Spacer(modifier = Modifier.height(25.dp))
            }

            // Menampilkan ProfileGuru
            item {
                ProfileGuru(
                    textjudul = nama,
                    textdecs = gelar,
                    painter = painter2,
                    modifier = Modifier
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Menampilkan TextC
            item {
                TextC(
                    textRes = detail,
                    size = 14,
                    modifier = Modifier,
                    colorHex = "#0a0a0a"
                )
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Menampilkan BtnGuru
            item {
                BtnGuru(modifier = Modifier)
            }
            item {
                Spacer(modifier = Modifier.height(15.dp))
            }

            // Menampilkan TextD kedua
            item {
                TextD(textRes = "Quizzes", size = 22, modifier = Modifier)
            }
            item {
                Spacer(modifier = Modifier.height(5.dp))
            }

            // Menampilkan daftar kuis dengan LazyColumn
            items(items) { item ->
                CardQuizGuru(
                    textjudul = item,
                    textdecs = item,
                    painter = painter,
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    onclick = {
                        navController.navigate(Routes.instruksi)
                    }
                )
            }
    }
}
}
@Preview(showBackground = true)
@Composable
fun PreviewKelas() {
    SiswaTheme {
        KelasLayout(
            remember { List(10) { "Item $it" } }, painterResource(id = R.drawable.anime), "hsjhjw", "ajsja", "ajjsajsjak",
            painterResource(
            id = R.drawable.anime,
        ),
            navController = NavController(LocalContext.current))
    }
}