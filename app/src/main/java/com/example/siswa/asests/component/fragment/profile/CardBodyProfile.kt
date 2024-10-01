package com.example.siswa.asests.component.fragment.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siswa.asests.component.element.ButtonA
import com.example.siswa.asests.component.element.ButtonB
import com.example.siswa.asests.component.element.TextD
import com.example.siswa.ui.theme.Routes

@Composable
fun BodyPro(navController: NavController){
    Column{
        TextD(textRes = "Pengaturan", size = 22, modifier = Modifier
            .padding(top = 20.dp)
            .padding(bottom = 5.dp))
        ButtonB(
            onClick = { navController.navigate(Routes.ubahpassword) },
            text = "Ubah Password",
            imageVector = Icons.Filled.Settings,
            contentDescription = "Setting",
            sizeImage = 30,
            tintColor = Color(0xFF141414),
            imageVector2 = Icons.Filled.ArrowBackIosNew,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 1.dp),
            shape = RoundedCornerShape(0.dp),
            fontsize = 15.sp
        )
        Spacer(modifier = Modifier.height(45.dp))
        TextD(textRes = "Lainnya", size = 22, modifier = Modifier
            .padding(top = 20.dp)
            .padding(bottom = 5.dp))
        ButtonB(
            onClick = { navController.navigate(Routes.tentang) },
            text = "Tentang Aplikasi",
            imageVector = Icons.Filled.Info,
            contentDescription = "Setting",
            sizeImage = 30,
            tintColor = Color(0xFF141414),
            imageVector2 = Icons.Filled.ArrowBackIosNew,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 1.dp),
            shape = RoundedCornerShape(0.dp),
            fontsize = 15.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        ButtonB(
            onClick = { /* Aksi ketika tombol ditekan */ },
            text = "Feedback Guru",
            imageVector = Icons.Filled.StarBorder,
            contentDescription = "Setting",
            sizeImage = 30,
            tintColor = Color(0xFF141414),
            imageVector2 = Icons.Filled.ArrowBackIosNew,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 1.dp),
            shape = RoundedCornerShape(0.dp),
            fontsize = 15.sp
        )

        Spacer(modifier = Modifier.height(15.dp))
        ButtonA(onClick = {navController.navigate(Routes.login)}, text = "Log Out")
    }
}