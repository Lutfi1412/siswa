package com.example.siswa.asests.component.element

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun ButtonA(
    modifier : Modifier = Modifier.fillMaxWidth(),
    onClick: () -> Unit,
    text: String,
    color: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF17C6ED)
    ),
    colortext : Color = Color(0xFFFFFFFF)
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        colors = color
    ) {
        Text(
            text,
            color = colortext
        )
    }
}

@Composable
fun IconButtonCenter(
    onClick: () -> Unit,
    text: String,
    imageVector: ImageVector,
    contentDescription: String,
    sizeImage: Int,
    tintColor: Color,
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(1.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White // Warna latar belakang putih
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = imageVector, // Ganti dengan ikon lain jika diinginkan
                contentDescription = contentDescription,
                tint = tintColor, // Atur warna ikon agar terlihat
                modifier = Modifier.size(sizeImage.dp) // Ukuran ikon
            )
            Text(
                text = text,
                color = tintColor
            )
        }
    }
}

@Composable
fun ButtonB(
    onClick: () -> Unit,
    text: String,
    imageVector: ImageVector,
    contentDescription: String,
    sizeImage: Int,
    imageVector2: ImageVector,
    modifier: Modifier,
    tintColor: Color,
    fontsize: TextUnit,
    shape: RoundedCornerShape
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White // Warna latar belakang putih
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = imageVector, // Ganti dengan ikon lain jika diinginkan
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(sizeImage.dp)
                    .padding(end = 12.dp),
                tint = tintColor// Ukuran ikon
            )
            Text(
                text = text,
                modifier = Modifier.weight(1f),
                color = tintColor,
                fontSize = fontsize
            )
            Icon(
                imageVector = imageVector2, // Ganti dengan ikon lain jika diinginkan
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(20.dp)
                    .graphicsLayer(scaleX = -1f),
                tint = tintColor
            )
        }
    }
}

@Composable
fun ButtonC(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String,
    tintColor: Color
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(1.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White // Warna latar belakang putih
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                color = tintColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButton() {
    SiswaTheme {
//        ButtonA(onClick = {}, text = "Login")
//        IconButtonCenter(onClick = {}, text = "Home", imageVector = Icons.Filled.Home, "home", 24, Color(0xFF17C6ED))
//        ButtonB(
//            onClick = { /* Aksi ketika tombol ditekan */ },
//            text = "Settings",
//            imageVector = Icons.Filled.Settings,
//            contentDescription = "Setting",
//            sizeImage = 40,
//            tintColor = Color(0xFF141414),
//            imageVector2 = Icons.Filled.ArrowBackIosNew,
//            modifier = Modifier
//                .fillMaxWidth()
//                .shadow(elevation = 4.dp),
//            shape = RoundedCornerShape(5.dp),
//            fontsize = 15.sp
//        )
        ButtonC(text = "1", tintColor = Color(0xFF141414))
    }
}