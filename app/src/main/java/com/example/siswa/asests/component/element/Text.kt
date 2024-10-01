package com.example.siswa.asests.component.element

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.siswa.R
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun TextA(
    @StringRes textRes: Int,
    size: Int,
    modifier: Modifier
){
    Text(
        text = stringResource(id = textRes),
        fontWeight = FontWeight.Bold, // Membuat teks tebal
        fontSize = size.sp, // Ukuran teks
        color = Color.Black, // Warna teks
        modifier = modifier // Padding untuk memberi jarak
    )
}

@Composable
fun TextB(
    @StringRes textRes: Int,
    size: Int,
    modifier: Modifier
){
    Text(
        text = stringResource(id = textRes),
        fontSize = size.sp, // Ukuran teks
        color = Color.Black, // Warna teks
        modifier = modifier,
        textAlign = TextAlign.Center // Padding untuk memberi jarak
    )
}

@Composable
fun TextC(
    textRes: String,
    size: Int,
    modifier: Modifier = Modifier, // Berikan nilai default untuk modifier
    colorHex: String  // Warna default
) {
    val color = Color(android.graphics.Color.parseColor(colorHex))

    Text(
        text = textRes,
        fontSize = size.sp, // Ukuran teks
        color = color,
        modifier = modifier,
    )
}

@Composable
fun TextD(
    textRes: String,
    size: Int,
    modifier: Modifier,
){
    Text(
        text = textRes,
        fontWeight = FontWeight.Bold,
        fontSize = size.sp, // Ukuran teks
        color = Color.Black,
        modifier = modifier,
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewText() {
    SiswaTheme {
//        TextA(textRes = R.string.login)
        TextB(textRes = R.string.loginchild, 20, modifier = Modifier)
    }
}