package com.example.siswa.asests.component.fragment.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siswa.R
import com.example.siswa.asests.component.element.ImageLogo
import com.example.siswa.asests.component.element.TextC
import com.example.siswa.asests.component.element.TextD
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun CardQuizGuru(
    textjudul: String,
    textdecs: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    onclick: () -> Unit ={}
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.clickable { onclick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(14.dp)
        ) {
            ImageLogo(
                painter = painter,
                DescLogo = "logomimath",
                Modifier
                    .width(98.dp)
                    .height(98.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                TextD(textRes = textjudul, size = 16, modifier = Modifier)
                Spacer(modifier = Modifier.height(5.dp))
                TextC(textRes = textdecs, size = 14, modifier = Modifier, colorHex = "#595854")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewCardQuiz() {
    SiswaTheme {
        CardQuizGuru(textjudul = "jajaj", textdecs = "jskajsk", painter = painterResource(id = R.drawable.anime), modifier = Modifier)
    }
}