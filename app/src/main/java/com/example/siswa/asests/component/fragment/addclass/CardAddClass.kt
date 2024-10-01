package com.example.siswa.asests.component.fragment.addclass

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.siswa.asests.component.element.ButtonA
import com.example.siswa.asests.component.element.KolomInputTambah
import com.example.siswa.asests.component.element.KolomIput
import com.example.siswa.asests.component.element.TextD
import com.example.siswa.ui.theme.Routes
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun AddClass (navController: NavController, modifier: Modifier = Modifier){
    val usernameState = remember { mutableStateOf("") }
    Column (
        modifier = modifier
    ){
        TextD(textRes = "Kode Kelas", size = 16, modifier = Modifier)
        Spacer(modifier = Modifier.height(7.dp))
        KolomInputTambah(value = usernameState.value, onValueChange = {usernameState.value = it}, label = "Masukan Kode Kelas")
        Spacer(modifier = Modifier.height(15.dp))
        ButtonA(onClick = { navController.navigate(
            Routes.addclass) }, text = "Bergabung")
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewAdd() {
//    SiswaTheme {
//        AddClass()
//    }
//}