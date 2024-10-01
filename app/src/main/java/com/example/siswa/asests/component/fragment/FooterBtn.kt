package com.example.siswa.asests.component.fragment

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.siswa.asests.component.element.IconButtonCenter

@Composable
fun FooterBtn(homeVisible: MutableState<Boolean>){
//    val homeVisible = remember { mutableStateOf(true) }
    val homeColor = if (homeVisible.value) Color(0xFF17C6ED) else Color(0xFF595854)
    val profileColor = if (homeVisible.value) Color(0xFF595854) else Color(0xFF17C6ED)
    Row(
        modifier = Modifier.fillMaxWidth()
    ){
        IconButtonCenter(
            onClick = {
                homeVisible.value = true
            },
            text = "Home",
            imageVector = Icons.Filled.Home,
            contentDescription = "Home",
            sizeImage = 24,
            tintColor = homeColor,
            modifier = Modifier.weight(1f)
        )
        IconButtonCenter(
            onClick = {
                homeVisible.value = false
            },
            text = "Profile",
            imageVector = Icons.Filled.Person,
            contentDescription = "Profile",
            sizeImage = 24,
            tintColor = profileColor,
            modifier = Modifier.weight(1f)
        )
    }
}