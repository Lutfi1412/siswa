package com.example.siswa.asests.component.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siswa.asests.component.element.TextC
import com.example.siswa.asests.component.element.TextD
import com.example.siswa.asests.component.fragment.BackBtn
import com.example.siswa.asests.component.fragment.quiz.CardQuiz
import com.example.siswa.ui.theme.Routes
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun DetailQuizLayout(
    items: List<String> = remember { List(2) { "Item $it" }},
    soal: Int = 10,
    benar : Int = 10,
    nilai: Int = 100,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackBtn(text = "Quiz Completed", modifier = Modifier.padding(vertical = 5.dp),
            onclik = {
            navController.navigate(
                Routes.hasil)
        })
        Spacer(modifier = Modifier.height(10.dp))
        TextD(textRes = "Quiz Completed", size = 25, modifier = Modifier.padding(horizontal = 15.dp))
        Spacer(modifier = Modifier.height(10.dp))
        TextC(textRes = "Total Questions: ${soal}, Correct Answers: ${benar}, Score: ${nilai}", size = 20, colorHex = "#193238", modifier = Modifier.padding(horizontal = 15.dp))
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier.padding(horizontal = 30.dp),
            contentPadding = PaddingValues(top = 10.dp) // Menambahkan padding di atas
        ) {
            items(items) { item ->
                CardQuiz(
                    soal = "<p>Apa warna favorit Anda?</p>",
                    opsi1 = item,
                    opsi2 = item,
                    opsi3 = item,
                    opsi4 = item
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailQuiz() {
    val navController = rememberNavController()
    SiswaTheme {
        DetailQuizLayout(navController = navController)
    }
}