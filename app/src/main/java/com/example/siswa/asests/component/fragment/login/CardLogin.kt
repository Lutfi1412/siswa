package com.example.siswa.asests.component.fragment.login

import android.os.Build
import validasiLogin
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.siswa.asests.component.element.ButtonA
import com.example.siswa.asests.component.element.KolomIput
import com.example.siswa.ui.theme.Routes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardLogin(navController: NavController){
    val fromProfile = false
    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }
    val usernameState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    val toggleVisibilityIcon = if (passwordVisible) {
        Icons.Filled.Visibility // Eye icon when password is visible
    } else {
        Icons.Filled.VisibilityOff // Eye icon when password is hidden
    }
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Mengatur warna latar belakang card
        ),
        elevation = CardDefaults.cardElevation(4.dp) // Menambahkan bayangan (shadow)
    ){
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Center horizontal alignment
        ) {
            KolomIput(
                value = usernameState.value,
                onValueChange = {usernameState.value = it},
                label = "Masukan NIS", visualTransformation = VisualTransformation.None,
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Password Icon") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            KolomIput(
                value = passwordState.value,
                onValueChange = {passwordState.value = it},
                label = "Masukan Password",
                imageVector = Icons.Filled.Lock,
                imageVector2 = toggleVisibilityIcon,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon", ) },
                trailingIcon = {
                    IconButton(onClick = { setPasswordVisible(!passwordVisible) }) {
                        Icon(toggleVisibilityIcon, contentDescription = "Toggle Password Visibility") // Trailing icon
                    }
                },
                isPasswordField = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            ButtonA(
                onClick = {
                    if (usernameState.value.isNotEmpty() && passwordState.value.isNotEmpty()) {
                        validasiLogin(
                            username = usernameState.value,
                            password = passwordState.value,
                            navigateTo = { route ->
                                navController.navigate(route)
                            },
                            context = context
                        )
                    }else if (usernameState.value.isNotEmpty() && passwordState.value.isEmpty()){
                        Toast.makeText(context, "Masukan Password", Toast.LENGTH_SHORT).show()
                    }else if (usernameState.value.isEmpty() && passwordState.value.isNotEmpty()){
                        Toast.makeText(context, "Masukan Nis", Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(context, "Masukan Nis dan Password", Toast.LENGTH_SHORT).show()
                    }
                },
                text = "Login"
            )
        }
    }
}