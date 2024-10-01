package com.example.siswa.asests.component.fragment.quiz

import android.text.Html
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.example.siswa.asests.component.fragment.BackBtn
import com.example.siswa.asests.component.fragment.home.CardScore
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun CardQuiz(
    modifier: Modifier = Modifier,
    soal: String,
    opsi1: String,
    opsi2: String,
    opsi3: String,
    opsi4: String
){
    Card (
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(1.dp),
            modifier = modifier.fillMaxWidth().padding(bottom = 15.dp)
        ) {
            Column (
                modifier = Modifier.padding(20.dp)
            ) {
                AndroidView(
                    factory = { context ->
                        LinearLayout(context).apply {
                            orientation = LinearLayout.VERTICAL

                            val questionTextView = TextView(context).apply {
                                text = HtmlCompat.fromHtml(soal, HtmlCompat.FROM_HTML_MODE_LEGACY)
                            }
                            addView(questionTextView)
                            val radioGroup = RadioGroup(context).apply {
                                orientation = RadioGroup.VERTICAL

                                val options = listOf(opsi1, opsi2, opsi3, opsi4)
                                options.forEach { option ->
                                    val radioButton = RadioButton(context).apply {
                                        text = HtmlCompat.fromHtml(option, HtmlCompat.FROM_HTML_MODE_LEGACY) // Memproses teks HTML
                                    }
                                    addView(radioButton)
                                }
                            }
                            addView(radioGroup)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
@Preview(showBackground = true)
@Composable
fun PreviewQuiz() {
    SiswaTheme {
        CardQuiz(soal = "<p>Apa warna favorit Anda?</p>", opsi1 = "<b>Apa warna favorit Anda?</b>", opsi2 = "ajsj", opsi3 = "ajsjk", opsi4 = "ajksak")
    }
}