package com.example.siswa.asests.component.layout

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.siswa.R
import com.example.siswa.asests.component.element.ImageLogoB
import com.example.siswa.asests.component.element.TextD
import com.example.siswa.asests.component.fragment.BackBtn
import com.example.siswa.ui.theme.Routes
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun TentangLayout(navController: NavController, fromProfile: Boolean = false){
 Column(
     modifier = Modifier
         .fillMaxSize()
 ) {
     BackBtn(text = "Tentang Aplikasi", modifier = Modifier.padding(vertical = 5.dp),
         onclik = { navController.navigate("${Routes.dashboard}/${fromProfile}")})
     ImageLogoB(
         painter = painterResource(id = R.drawable.anime1),
         DescLogo = "Anime",
         Modifier
             .fillMaxWidth()
             .height(240.dp)
             .clip(RoundedCornerShape(0.dp))
             .padding(horizontal = 20.dp),
         contentScale = ContentScale.Crop
     )
     Spacer(modifier = Modifier.height(27.dp))
     TextD(textRes = "Tentang Aplikasi", size = 22, modifier = Modifier.padding(horizontal = 20.dp))
     Spacer(modifier = Modifier.height(5.dp))
     TextD(textRes = "ajhjahsja", size = 14, modifier = Modifier.padding(horizontal = 20.dp))
 }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewTentang() {
//    SiswaTheme {
//        TentangLayout()
//    }
//}