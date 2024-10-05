package com.example.siswa.asests.component.fragment.home

import ImgUrl
import android.content.Context
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.siswa.R
import com.example.siswa.asests.component.element.GetUrlImg
import com.example.siswa.asests.db.UsersViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import io.github.jan.supabase.postgrest.from
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HeaderHome(
    scrollState: LazyListState,
    navController: NavController,
    searchState : String,
    onSearchQueryChange: (String) -> Unit
) {
    val pagerState = rememberPagerState()

    // Mengambil URL gambar dari Supabase
    var imageUrls by remember { mutableStateOf(listOf<ImgUrl>()) }

    LaunchedEffect(Unit) {
        imageUrls = ImgUrl()
    }

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
            .height(214.dp)
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = if (offset == maxOffset) 1.dp else 0.dp
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .graphicsLayer { alpha = 1f - offsetProgress }
            ) {
                Box(modifier = Modifier.padding(top = 17.dp)) {
                    // Slider Gambar
                    if (imageUrls.isNotEmpty()) {
                        HorizontalPager(
                            count = imageUrls.size,
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(RoundedCornerShape(0.dp))
                        ) { page ->
                            val imageUrl = imageUrls[page].url
                            GetUrlImg(
                                url = imageUrl,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .padding(horizontal = 15.dp),
                                contentScale = ContentScale.FillBounds
                            )
                        }

                        // Indikator untuk menunjukkan halaman aktif
                        HorizontalPagerIndicator(
                            pagerState = pagerState,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(16.dp),
                            activeColor = colorResource(id = R.color.white),
                            inactiveColor = colorResource(id = R.color.black),
                            indicatorWidth = 8.dp,
                            indicatorHeight = 8.dp
                        )
                    }
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
                        .padding(top = 15.dp),
                    navController,
                    searchState,
                    onSearchQueryChange)
            }
        }
    }
}


suspend fun ImgUrl(): List<ImgUrl> {
    return try {
        UsersViewModel().supabase.from("banners").select().decodeList<ImgUrl>()
    } catch (e: Exception) {
        Log.e("ImgUrlError", e.message ?: "Unknown error")
        emptyList() // Jika terjadi error, return list kosong
    }
}

