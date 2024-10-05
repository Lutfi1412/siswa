package com.example.siswa.asests.component.layout

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siswa.asests.component.fragment.profile.BodyPro
import com.example.siswa.asests.component.fragment.profile.HeaderPro
import com.example.siswa.ui.theme.SiswaTheme
import getDataNama
import getDataUsername

@Composable
fun ProfileLayout(navController: NavController, context: Context = LocalContext.current){
    Column(
        modifier = Modifier
            .padding(top = 40.dp)
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderPro(nis = getDataUsername(context).toString(), nama = getDataNama(context).toString())
        BodyPro(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewImageProfile() {
    val navController = rememberNavController()
    SiswaTheme {
        ProfileLayout(navController)
    }
}