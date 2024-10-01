package com.example.siswa.asests.component.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siswa.R
import com.example.siswa.asests.component.element.TextB
import com.example.siswa.asests.component.element.TextC
import com.example.siswa.asests.component.element.TextD
import com.example.siswa.asests.component.fragment.BackBtn
import com.example.siswa.asests.component.fragment.kelas.BtnGuru
import com.example.siswa.ui.theme.Routes
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun InstruksiLayout(navController: NavController){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackBtn(text = "Welcome To The Quiz", modifier = Modifier.padding(vertical = 5.dp), onclik = { navController.navigate(
            Routes.kelas) })
        Spacer(modifier = Modifier.height(10.dp))
        TextD(textRes = "Feedback", size = 22, modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(modifier = Modifier.height(2.dp))
        TextC(textRes = "aidsdjhjsdbcnb", size = 14, modifier = Modifier.padding(horizontal = 20.dp), colorHex = "#193238")
        Spacer(modifier = Modifier.height(20.dp))
        TextD(textRes = "Instruksi", size = 18, modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(modifier = Modifier.height(2.dp))
        TextC(textRes = "1. Read each question carefully.\n2. Select the best answer from the choices provided.\n3. You can skip questions, but try to answer as many as possible.\n4. Click 'Submit' when you are done.", size = 14, modifier = Modifier.padding(horizontal = 20.dp), colorHex = "#193238")
        Spacer(modifier = Modifier.height(17.dp))
        BtnGuru(
            modifier = Modifier.padding(horizontal = 20.dp),
            text1 = "Start Quiz",
            text2 = "Lihat Hasil",
            onclik = {navController.navigate(Routes.quiz)},
            onclik2 = {navController.navigate(Routes.hasil)},
            color = ButtonDefaults.buttonColors(containerColor = Color(0xFFEBEDED)),
            colortext =  Color(0xFF000000)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInstruksi() {
    val navController = rememberNavController()
    SiswaTheme {
        InstruksiLayout(navController)
    }
}