package com.example.siswa.asests.component.element

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.siswa.R
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun ImageLogo(
    painter: Painter,
    DescLogo: String,
    modifier: Modifier,
) {
    Image(
        painter = painter,
        contentDescription = DescLogo,
        modifier = modifier,
        alignment = Alignment.Center
    )
}

@Composable
fun ImageLogoB(
    painter: Painter,
    DescLogo: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale
) {
    Image(
        painter = painter,
        contentDescription = DescLogo,
        modifier = modifier,
        alignment = Alignment.Center,
        contentScale = contentScale
    )
}


@SuppressLint("NewApi")
@Preview(showBackground = true)
@Composable
fun PreviewImage() {
    val navController = rememberNavController()
    SiswaTheme {
        ImageLogo(
            painterResource(id = R.drawable.anime),
            "logomimath",
            Modifier
                .width(120.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(20.dp)
        ))
    }
}