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
fun CardKelas(
    textjudul: String,
    textdecs: String,
    painter: Painter,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }, // Mengurangi padding Card menjadi 8dp
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp) // Mengurangi padding Row menjadi 8dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                TextD(textRes = textjudul, size = 16, modifier = Modifier)
                Spacer(modifier = Modifier.height(5.dp))
                TextC(textRes = textdecs, size = 14, modifier = Modifier, colorHex = "#595854")
            }
            Spacer(modifier = Modifier.width(10.dp)) // Mengurangi jarak antara teks dan gambar
            ImageLogo(
                painter = painter,
                DescLogo = "logomimath",
                Modifier
                    .width(98.dp)
                    .height(98.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewImage() {
    SiswaTheme {
        CardKelas(textjudul = "jajaj", textdecs = "jskajsk", painter = painterResource(id = R.drawable.anime))
    }
}