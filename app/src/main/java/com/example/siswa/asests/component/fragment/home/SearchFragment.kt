package com.example.siswa.asests.component.fragment.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.siswa.asests.component.element.kolominputB
import com.example.siswa.ui.theme.Routes

@Composable
fun Search(
    modifier: Modifier,
    navController:NavController
){
    val searchState = remember { mutableStateOf("") }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        kolominputB(
            value = searchState.value,
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "search") },
            onValueChange = { searchState.value = it },
            trailingIcon = {
                if (searchState.value.isNotEmpty()) {
                    IconButton(onClick = { searchState.value = "" }) {
                        Icon(Icons.Filled.Cancel, contentDescription = "cancel Search")
                    }
                }
            },
            shape = 50,
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
        )
        IconButton(onClick ={ navController.navigate(
            Routes.addclass) }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Item"
            )
        }
    }
}