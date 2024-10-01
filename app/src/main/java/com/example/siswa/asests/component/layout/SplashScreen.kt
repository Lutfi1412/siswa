package com.example.siswa.asests.component.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siswa.R
import com.example.siswa.asests.component.element.ImageLogo

// A simple loading screen composable
@Composable
fun LoadingScreen() {
    // Using Box to center the ImageLogo
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // Center the content
    ) {
        ImageLogo(
            painter = painterResource(id = R.drawable.updatelogo),
            DescLogo = "Logo MiMath",
            Modifier
                .width(256.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(0.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingScreen() {
    LoadingScreen()
}
