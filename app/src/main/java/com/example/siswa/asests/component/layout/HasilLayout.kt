package com.example.siswa.asests.component.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siswa.R
import com.example.siswa.asests.component.element.ImageLogo
import com.example.siswa.asests.component.element.ImageLogoB
import com.example.siswa.asests.component.element.TextD
import com.example.siswa.asests.component.fragment.BackBtn
import com.example.siswa.asests.component.fragment.home.CardScore
import com.example.siswa.asests.component.fragment.kelas.BtnGuru
import com.example.siswa.ui.theme.Routes
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun HasilLayout (
    feedback: String,
    nilai : String,
    navController:NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackBtn(text = "Hasil Quiz", modifier = Modifier.padding(vertical = 5.dp),
            onclik = {
            navController.navigate(
                Routes.kelas)
        })
        ImageLogoB(
            painter = painterResource(id = R.drawable.anime1),
            DescLogo = "Anime",
            Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(0.dp))
                .padding(horizontal = 20.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(25.dp))
        TextD(textRes = "Hasil Akhir", size = 25, modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(modifier = Modifier.height(15.dp))
        CardScore(
            textjudul = nilai,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 20.dp))
        Spacer(modifier = Modifier.height(15.dp))
        BtnGuru(
            modifier = Modifier.padding(horizontal = 20.dp),
            text1 = "Lihat Hasil",
            text2 = "Kembali Ke Home",
            color = ButtonDefaults.buttonColors(containerColor = Color(0xFFEBEDED)),
            colortext =  Color(0xFF000000),
            onclik = {
                navController.navigate(
                    Routes.detailquiz)
            },
            onclik2 = {
                navController.navigate(
                    Routes.kelas)
            }
        )

        Spacer(modifier = Modifier.height(30.dp))
        TextD(textRes = "Feedback", size = 28, modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(modifier = Modifier.height(5.dp))
        TextD(textRes = feedback, size = 22, modifier = Modifier.padding(horizontal = 20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHsil() {
    val navController = rememberNavController()
    SiswaTheme {
        HasilLayout(nilai = "89", feedback = "ajshja", navController = navController)
    }
}