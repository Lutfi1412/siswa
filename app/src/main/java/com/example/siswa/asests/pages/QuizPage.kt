package com.example.siswa.asests.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siswa.asests.component.element.ButtonA
import com.example.siswa.asests.component.element.ButtonC
import com.example.siswa.asests.component.element.TextC
import com.example.siswa.asests.component.element.TextD
import com.example.siswa.asests.component.layout.QuizLayout
import com.example.siswa.ui.theme.Routes
import com.example.siswa.ui.theme.SiswaTheme
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

@Composable
fun Quiz(navController: NavController){
    val items: List<String> = remember { List(18) { "Item $it" } }
    var currentPage by remember { mutableIntStateOf(1) }        // Parameter untuk halaman saat ini
    val itemsPerPage = 5        // Parameter untuk jumlah item per halaman
    val totalPages: Int = (items.size + itemsPerPage - 1) / itemsPerPage // Total halaman dihitung dari item dan itemsPerPage
    val context = LocalContext.current

    BackHandler {
        Toast.makeText(context, "Tidak dapat keluar quiz, selesaikan terlebih dahulu", Toast.LENGTH_SHORT).show()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextD("Quiz", 18, Modifier)
        Spacer(modifier = Modifier.height(15.dp))

        Row {
            for (page in 1..totalPages) {
                ButtonC(
                    text = page.toString(),
                    tintColor = if (page == currentPage) Color(0xFF17C6ED) else Color(0xFF141414),
                    modifier = Modifier.weight(1f).padding(horizontal = 3.dp),
                    onClick = { currentPage = page }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        QuizLayout(
            currentPage = currentPage,
            itemsPerPage = itemsPerPage,
            items =  items,
            modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (currentPage > 1) {
                ButtonA(
                    onClick = { currentPage-- },
                    modifier = Modifier.weight(1f),
                    color = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEBEDED)
                    ),
                    colortext = Color(0xFF193238),
                    text = "Sebelumnya"
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.width(20.dp))

            ButtonA(
                onClick = {
                    if (currentPage < totalPages) {
                        currentPage++
                    } else{
                        navController.navigate(Routes.hasil)
                    }
                },
                modifier = Modifier.weight(1f),
                text = if (currentPage < totalPages) "Berikutnya" else "Finish"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuiz() {
    val navController = rememberNavController()
    SiswaTheme {
        Quiz(navController)
    }
}