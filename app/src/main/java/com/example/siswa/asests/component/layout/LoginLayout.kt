package com.example.siswa.asests.component.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siswa.R
import com.example.siswa.asests.component.element.ImageLogo
import com.example.siswa.asests.component.element.TextA
import com.example.siswa.asests.component.element.TextB
import com.example.siswa.asests.component.fragment.login.CardLogin
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun LoginLayout (navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            ImageLogo(
                painter = painterResource(id = R.drawable.updatelogo),
                DescLogo = "Logo MiMath",
                Modifier
                    .width(256.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(0.dp)))
            TextA(textRes = R.string.login, 22, modifier = Modifier)
            Spacer(modifier = Modifier.padding(8.dp))
            TextB(textRes = R.string.loginchild, 14, modifier = Modifier)
            Spacer(modifier = Modifier.padding(10.dp))
            CardLogin(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewImage() {
    val navController = rememberNavController()
    SiswaTheme {
        LoginLayout(navController)
    }
}
