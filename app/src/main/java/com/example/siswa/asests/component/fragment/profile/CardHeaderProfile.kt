package com.example.siswa.asests.component.fragment.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siswa.R
import com.example.siswa.asests.component.element.ImageLogo
import com.example.siswa.asests.component.element.TextA
import com.example.siswa.asests.component.element.TextC
import com.example.siswa.asests.component.element.TextD
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun HeaderPro (
    nis : String,
    nama : String
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextA(textRes = R.string.profile, size = 18,
            modifier = Modifier.padding(bottom = 15.dp))
        ImageLogo(painter = painterResource(id = R.drawable.anime), DescLogo = "profile img",
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(100.dp))
        )
        TextD(textRes = nama, size = 22,
            modifier = Modifier.padding(top = 15.dp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        TextC(textRes = "NIS : $nis", size = 15,
            modifier = Modifier
                .padding(top = 5.dp),
            colorHex = "#595854"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewImageProfile() {
    SiswaTheme {
        val nis : String = "jay"
        val nama : String = "jay"
        HeaderPro(nis, nama)
    }
}