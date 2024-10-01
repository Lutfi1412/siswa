package com.example.siswa.asests.component.fragment.sandi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siswa.asests.component.element.ButtonA
import com.example.siswa.asests.component.element.KolomIput
import com.example.siswa.ui.theme.Routes
import com.example.siswa.ui.theme.SiswaTheme

@Composable
fun UbahSandi(navController: NavController = rememberNavController(), modifier: Modifier = Modifier){
    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }
    val passwordState = remember { mutableStateOf("") }
    val usernameState = remember { mutableStateOf("") }
    val toggleVisibilityIcon = if (passwordVisible) {
        Icons.Filled.Visibility // Eye icon when password is visible
    } else {
        Icons.Filled.VisibilityOff // Eye icon when password is hidden
    }

    val (confirmPassword, setconfirmPasswordVisible) = remember { mutableStateOf(false) }
    val confirmPasswordState = remember { mutableStateOf("") }
    val ConfirmtoggleVisibilityIcon = if (confirmPassword) {
        Icons.Filled.Visibility // Eye icon when password is visible
    } else {
        Icons.Filled.VisibilityOff // Eye icon when password is hidden
    }
    Column (
        modifier = modifier
    ){

        KolomIput(
            value = usernameState.value,
            onValueChange = {usernameState.value = it},
            label = "Kata Sandi Saat Ini", visualTransformation = VisualTransformation.None,
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Password Icon") }
        )
        Spacer(modifier = Modifier.height(15.dp))
        KolomIput(
            value = passwordState.value,
            onValueChange = {passwordState.value = it},
            label = "Kata Sandi Baru",
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
        Spacer(modifier = Modifier.height(15.dp))

        KolomIput(
            value = confirmPasswordState.value,
            onValueChange = {confirmPasswordState.value = it},
            label = "Konfirmasi Kata Sandi Baru",
            imageVector = Icons.Filled.Lock,
            imageVector2 = ConfirmtoggleVisibilityIcon,
            visualTransformation = if (confirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon", ) },
            trailingIcon = {
                IconButton(onClick = { setconfirmPasswordVisible(!confirmPassword) }) {
                    Icon(ConfirmtoggleVisibilityIcon, contentDescription = "Toggle Password Visibility") // Trailing icon
                }
            },
            isPasswordField = true
        )
        Spacer(modifier = Modifier.height(15.dp))
        ButtonA(
            onClick = { navController.navigate(Routes.dashboard) },
            text = "Simpan"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUbah() {
    SiswaTheme {
        UbahSandi()
    }
}