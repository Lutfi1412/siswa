package com.example.siswa.asests.component.fragment.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.siswa.R
import com.example.siswa.asests.component.element.ImageLogoB
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HeaderHome(scrollState: LazyListState, navController: NavController) {
    val pagerState = rememberPagerState()
    val images = listOf(R.drawable.promo, R.drawable.promo, R.drawable.promo)
    val imageHeight = 214.dp - 75.dp

    val maxOffset = with(LocalDensity.current) {
        imageHeight.roundToPx()
    } - LocalWindowInsets.current.systemBars.layoutInsets.top

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = Color(0xFFfafafa),
        modifier = Modifier
            .height(
                214.dp
            )
            .offset {
                IntOffset(x = 0, y = -offset)
            },
        elevation = if (offset == maxOffset) 1.dp else 0.dp
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .graphicsLayer {
                        alpha = 1f - offsetProgress
                    }
            ) {
                Box(modifier = Modifier.padding(top = 17.dp)) {
                    // Slider Gambar
                    HorizontalPager(
                        count = images.size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp) // Atur tinggi slider
                            .clip(RoundedCornerShape(0.dp))
                    ) { page ->
                        ImageLogoB(
                            painter = painterResource(id = images[page]),
                            DescLogo = "Image slider",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    // Indikator untuk menunjukkan halaman aktif
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.BottomCenter) // Menempatkan indikator di bawah gambar
                            .padding(16.dp), // Menambahkan padding agar tidak terlalu dekat dengan tepi gambar
                        activeColor = colorResource(id = R.color.white),
                        inactiveColor = colorResource(id = R.color.black),
                        indicatorWidth = 8.dp,
                        indicatorHeight = 8.dp
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(214.dp)
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Search(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .padding(bottom = 10.dp)
                        .padding(top = 15.dp), navController)
            }
        }
    }
}