package com.example.siswa.asests.component.fragment.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.siswa.R
import com.example.siswa.ui.theme.Routes

@Composable
fun ContentHome(scrollState: LazyListState, navController: NavController) {
    LazyColumn(contentPadding = PaddingValues(top = 214.dp), state = scrollState) {
        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp).padding(top = 10.dp)
            ) {
                for (i in 0..15) {
                    CardKelas(
                        textjudul = "item $i",
                        textdecs = "item $i",
                        painter = painterResource(id = R.drawable.anime),
                        onClick = {
                            navController.navigate(Routes.kelas) // Navigasi saat Card ditekan
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}