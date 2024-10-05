package com.example.siswa.asests.component.layout

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siswa.asests.component.fragment.home.ContentHome
import com.example.siswa.asests.component.fragment.home.HeaderHome
import com.example.siswa.ui.theme.SiswaTheme

@Preview(showBackground = true)
@Composable
fun PreviewHomeLayout() {
    SiswaTheme {
        val navController = rememberNavController()
        HomeLayout(navController)
    }
}

@Composable
fun HomeLayout(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    var searchQuery by remember { mutableStateOf("") }

    Box (modifier){
        ContentHome(scrollState, navController, searchQuery = searchQuery)
        HeaderHome(scrollState, navController, searchQuery, onSearchQueryChange = { searchQuery = it })
    }
}


