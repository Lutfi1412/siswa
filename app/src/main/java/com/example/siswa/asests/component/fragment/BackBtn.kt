package com.example.siswa.asests.component.fragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siswa.asests.component.element.TextD
import com.example.siswa.ui.theme.SiswaTheme
import org.w3c.dom.Text

@Composable
fun BackBtn (
    text: String,
    modifier: Modifier,
    onclik: () -> Unit = {}

){
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 15.dp)
    ) {
        IconButton(
            onClick = onclik,
            modifier = Modifier.weight(1f, fill = false) // Memberikan berat pada IconButton
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = "Back"
            )
        }

        Spacer(modifier = Modifier.weight(1f)) // Spacer untuk mendorong teks ke tengah

        TextD(
            textRes = text,
            size = 18,
            modifier = modifier
                 // Menggunakan fillMaxWidth dengan batas tertentu untuk menampilkan teks dalam satu baris
        )

        Spacer(modifier = Modifier.weight(1f)) // Spacer di sisi kanan
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBack() {
    SiswaTheme {
        BackBtn("Ubah Kata Sandi", modifier = Modifier.fillMaxWidth(0.5f))
    }
}