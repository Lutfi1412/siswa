package com.example.siswa.asests.component.element

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.siswa.ui.theme.SiswaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KolomIput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    imageVector: ImageVector = Icons.Filled.Person,
    imageVector2: ImageVector? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isPasswordField: Boolean = false,
) {
    var isFocused by remember { mutableStateOf(false) } // State untuk melacak fokus

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
            isFocused = it.isNotEmpty() || isFocused // Update isFocused berdasarkan input
        },
        label = {
            Text(
                text = label,

                fontSize = 12.sp,
                color = when {
                    isFocused || value.isNotEmpty() -> Color(0xFF17C6ED) // Berwarna biru jika fokus atau ada input
                    else -> Color.Gray // Berwarna abu-abu jika kosong dan tidak fokus
                }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocused =
                    focusState.isFocused || value.isNotEmpty() // Update isFocused saat fokus atau ada input
            }
            .clip(RoundedCornerShape(5.dp)),
        visualTransformation = if (isPasswordField) visualTransformation else VisualTransformation.None,
        leadingIcon = {
            if (leadingIcon != null) {
                // Render leading icon with dynamic tint
                leadingIcon()
                Icon(
                    imageVector = imageVector, // Replace with your leading icon
                    contentDescription = "Password Icon",
                    tint = if (isFocused || value.isNotEmpty()) Color(0xFF17C6ED) else Color.Gray
                )
            }
        },
        trailingIcon = {if (trailingIcon != null){
            trailingIcon()
            if (imageVector2 != null) {
                Icon(
                    imageVector = imageVector2, // Replace with your leading icon
                    contentDescription = "Password Icon",
                    tint = if (isFocused || value.isNotEmpty()) Color(0xFF17C6ED) else Color.Gray
                )
            }
        } },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF17C6ED), // Warna garis ketika fokus
            unfocusedBorderColor = if (value.isNotEmpty()) Color(0xFF17C6ED) else Color.Gray, // Warna garis ketika tidak fokus
            focusedLabelColor = Color(0xFF17C6ED), // Warna label ketika fokus
            unfocusedLabelColor = Color.Gray,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KolomInputTambah(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    var isFocused by remember { mutableStateOf(false) } // State untuk melacak fokus

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
            isFocused = it.isNotEmpty() || isFocused // Update isFocused berdasarkan input
        },
        label = {
            Text(
                text = label,

                fontSize = 12.sp,
                color = when {
                    isFocused || value.isNotEmpty() -> Color(0xFF17C6ED) // Berwarna biru jika fokus atau ada input
                    else -> Color.Gray // Berwarna abu-abu jika kosong dan tidak fokus
                }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocused =
                    focusState.isFocused || value.isNotEmpty() // Update isFocused saat fokus atau ada input
            }
            .clip(RoundedCornerShape(5.dp)),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF17C6ED), // Warna garis ketika fokus
            unfocusedBorderColor = if (value.isNotEmpty()) Color(0xFF17C6ED) else Color.Gray, // Warna garis ketika tidak fokus
            focusedLabelColor = Color(0xFF17C6ED), // Warna label ketika fokus
            unfocusedLabelColor = Color.Gray,
        )
    )
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun kolominputB(
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    shape: Int,
    modifier: Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = "Search",
                textAlign = TextAlign.Center
            )
        },

        shape = RoundedCornerShape(shape.dp), // Radius sudut 8 dp
        modifier = modifier,
        leadingIcon = {
            if (leadingIcon != null) {
                Box(modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(end = 5.dp)
                    .padding(top = 5.dp)) { // Tambahkan margin start 8dp
                    leadingIcon()
                }
            }
        },
        trailingIcon = {
            if (trailingIcon != null) {
                Box(modifier = Modifier.padding(end = 15.dp)) { // Tambahkan margin start 8dp
                    trailingIcon()
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewInput() {
//    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }
//    val passwordState = remember { mutableStateOf("") }
    val searchState = remember { mutableStateOf("") }
//    val toggleVisibilityIcon = if (passwordVisible) {
//        Icons.Filled.Visibility // Eye icon when password is visible
//    } else {
//        Icons.Filled.VisibilityOff // Eye icon when password is hidden
//    }
    SiswaTheme {
//        KolomIput(
//            value = passwordState.value,
//            onValueChange = {passwordState.value = it},
//            label = "Masukan Password",
//            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon") },
//            trailingIcon = {
//                IconButton(onClick = { setPasswordVisible(!passwordVisible) }) {
//                    Icon(toggleVisibilityIcon, contentDescription = "Toggle Password Visibility")
//                }
//            },
//            isPasswordField = true
//        )
//        kolominputB(
//            value = searchState.value,
//            leadingIcon = {Icon(Icons.Filled.Search, contentDescription = "search")},
//            onValueChange = {searchState.value = it},
//            trailingIcon = {
//                if (searchState.value.isNotEmpty()) {
//                    IconButton(onClick = { searchState.value = "" }) {
//                        Icon(Icons.Filled.Cancel, contentDescription = "cancel Search")
//                    }
//                }
//            },
//            shape = 30,
//            modifier = Modifier.height(50.dp)
//        )
        KolomInputTambah(value = searchState.value, onValueChange = {searchState.value = it}, label = "Masukan Kode Kelas")
    }
}