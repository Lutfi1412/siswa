package com.example.siswa.asests.component.fragment.kelas

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siswa.R
import com.example.siswa.asests.component.element.ButtonA
import com.example.siswa.asests.component.fragment.home.CardQuizGuru
import com.example.siswa.ui.theme.Routes
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun BtnGuru(
    modifier: Modifier,
    text1 : String = "Link Meet",
    text2 : String = "Link Youtobe",
    color: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFFFF437D)
    ),
    colortext : Color = Color(0xFFFFFFFF),
    onclik: () -> Unit = {},
    onclik2: () -> Unit = {}
){
    Column (modifier = modifier) {
        ButtonA(
            onClick = {onclik()},
            text = text1
        )
        ButtonA(
            onClick = {onclik2()},
            text = text2,
            color = color,
            colortext = colortext
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewCardQuiz() {
    SiswaTheme {
        BtnGuru(modifier = Modifier)
    }
}