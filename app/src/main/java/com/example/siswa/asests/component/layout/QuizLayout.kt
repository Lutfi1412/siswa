package com.example.siswa.asests.component.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.siswa.asests.component.fragment.quiz.CardQuiz

@Composable
fun QuizLayout(
    modifier: Modifier = Modifier,
    items: List<String>,
    currentPage: Int,
    itemsPerPage: Int
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 10.dp)
    ) {
        items(items = items.drop((currentPage - 1) * itemsPerPage).take(itemsPerPage),
            key = { index -> // Tambahkan parameter key di sini
                "${currentPage}_${index}" // Buat key unik berdasarkan halaman dan index
            }
        ) { item ->
            CardQuiz(
                soal = "<p>Apa warna favorit Anda?</p>",
                opsi1 = item,
                opsi2 = item,
                opsi3 = item,
                opsi4 = item
            )
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun PreviewDasboard() {
//    val items: List<String> = remember { List(10) { "Item $it" } }
//    var currentPage by remember { mutableStateOf(1) }        // Parameter untuk halaman saat ini
//    val itemsPerPage = 5        // Parameter untuk jumlah item per halaman
//    val totalPages: Int = (items.size + itemsPerPage - 1) / itemsPerPage
//    SiswaTheme {
//        QuizLayout(items= items, currentPage = currentPage, itemsPerPage = itemsPerPage)
//    }
//}