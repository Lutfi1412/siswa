package com.example.siswa.asests.component.fragment.kelas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
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
fun ProfileGuru (
    textjudul: String,
    textdecs: String,
    painter: Painter,
    modifier: Modifier = Modifier){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        ImageLogo(
            painter = painter,
            DescLogo = "logomimath",
            Modifier
                .width(60.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(100.dp))
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            TextD(textRes = textjudul, size = 16, modifier = Modifier)
            Spacer(modifier = Modifier.height(5.dp))
            TextC(textRes = textdecs, size = 12, modifier = Modifier, colorHex = "#595854")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBack() {
    SiswaTheme {
        ProfileGuru("jksjks", "aksak", painterResource(id = R.drawable.anime), modifier = Modifier)
    }
}