package com.example.siswa

import ImgUrl
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.example.siswa.asests.component.HtmlText
import com.example.siswa.asests.component.fragment.quiz.CardQuiz
import com.example.siswa.asests.db.UsersViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.serialization.Serializable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Atur warna status bar berdasarkan tema
            ChangeStatusBarColor()
            Column(
                modifier = Modifier
                    .systemBarsPadding()
                    .fillMaxSize()
            ) {
//                AppNavigasi()
                QuizApp()

            }

        }
    }

    @Composable
    fun ChangeStatusBarColor() {
        // Deteksi apakah tema gelap diaktifkan
        val isDarkTheme = isSystemInDarkTheme()

        // Ubah warna status bar sesuai tema
        val statusBarColor = if (isDarkTheme) {
            androidx.compose.ui.graphics.Color.Black
        } else {
            androidx.compose.ui.graphics.Color.White
        }

        // Set status bar color (harus di luar Composable)
        window.statusBarColor = statusBarColor.toArgb()

        // Jika API level mendukung (API 23+), atur warna teks status bar (icon dan teks di status bar)
        if (!isDarkTheme) {
            // Teks status bar hitam untuk background status bar terang
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility or android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            // Teks status bar putih untuk background status bar gelap
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility and android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }
        private fun enableEdgeToEdge() {
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility or android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE or android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}

// Data class untuk menyimpan skor
// Data class untuk menyimpan skor
data class Skor(val jumlahSoal: Int, var jumlahBenar: Int = 0) {
    val nilai: Int
        get() {
            val calculatedValue = if (jumlahSoal > 0) 100.0 / jumlahSoal * jumlahBenar else 0.0
            return if (calculatedValue - calculatedValue.toInt() >= 0.4) {
                calculatedValue.toInt() + 1 // Naik satu jika desimal >= 0.3
            } else {
                calculatedValue.toInt() // Tetap jika desimal < 0.3
            }
        }
}


// Data class Quiz seperti yang sudah ada
@Serializable
data class Quiz(
    val id: Int,
    val question_text: String,
    val option_a: String,
    val option_b: String,
    val option_c: String,
    val option_d: String,
    val correct_answer: String
)

// Data class untuk menyimpan jawaban yang dipilih oleh user
data class ResponUser(val question: String, var selectedAnswer: String? = null)

@Composable
fun QuizApp() {
    val context = LocalContext.current

    // State untuk menyimpan jawaban user
    val userResponses = remember { mutableStateListOf<ResponUser>() }

    // State untuk melacak halaman saat ini
    var currentPage by remember { mutableStateOf(0) }

    // State untuk menyimpan daftar pertanyaan
    var questions by remember { mutableStateOf<List<Quiz>>(emptyList()) }

    // State untuk menyimpan skor
    var skor by remember { mutableStateOf(Skor(jumlahSoal = 0)) }

    // Mengambil pertanyaan dari Supabase saat komponen pertama kali diluncurkan
    LaunchedEffect(Unit) {
        questions = ReadQuiz(context) // Mengambil pertanyaan dari Supabase

        // Menginisiasi userResponses dengan data kosong sesuai jumlah pertanyaan
        if (userResponses.isEmpty() && questions.isNotEmpty()) {
            userResponses.addAll(questions.map { ResponUser(it.question_text) })
            skor = Skor(jumlahSoal = questions.size) // Set skor total berdasarkan jumlah pertanyaan
        }
    }

    // Hitung jumlah pertanyaan yang ditampilkan per halaman
    val itemsPerPage = 7
    val startIndex = currentPage * itemsPerPage
    val endIndex = minOf(startIndex + itemsPerPage, questions.size)

    // Pastikan list sudah terisi sebelum ditampilkan
    if (userResponses.isNotEmpty()) {
        Column {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(
                    items = questions.subList(startIndex, endIndex),
                    key = { quiz -> quiz.id } // Menggunakan id sebagai key
                ) { quiz ->
                    val userResponse = userResponses[questions.indexOf(quiz)]
                    CardQuiz2(quiz = quiz, userResponse = userResponse) { answer ->
                        userResponse.selectedAnswer = answer
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Navigasi antara halaman
            Row {
                if (currentPage > 0) {
                    Button(onClick = {
                        currentPage -= 1 // Kembali ke halaman sebelumnya
                    }) {
                        Text("Sebelumnya")
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                if (endIndex < questions.size) {
                    Button(onClick = {
                        currentPage += 1 // Pindah ke halaman berikutnya
                    }) {
                        Text("Next")
                    }
                } else {
                    // Tombol selesai di halaman terakhir
                    Button(onClick = {
                        // Menghitung jawaban yang benar
                        userResponses.forEachIndexed { index, response ->
                            if (response.selectedAnswer == questions[index].correct_answer) {
                                skor.jumlahBenar++ // Hitung jumlah jawaban benar
                            }
                            Log.d("QuizLog", "Pertanyaan ${index + 1}: ${response.question}, Jawaban: ${response.selectedAnswer}, Benar: ${questions[index].correct_answer}")
                        }

                        // Simpan skor dan tampilkan ke Log
                        Log.d("Skor", "Jumlah soal: ${skor.jumlahSoal}, Jawaban benar: ${skor.jumlahBenar}, Nilai: ${skor.nilai}")

                        // Reset Skor jika ingin mengulang quiz
                        skor = Skor(jumlahSoal = questions.size)

                        Toast.makeText(context, "Jawaban tersimpan dan skor dikirim ke Log", Toast.LENGTH_SHORT).show()
                    }) {
                        Text("Selesai")
                    }
                }
            }
        }
    } else {
        // Jika userResponses belum terisi, tampilkan pesan loading atau progress bar
        Text(text = "Memuat pertanyaan...")
    }
}

suspend fun ReadQuiz(context: Context): List<Quiz> {
    return try {
        // Pastikan UsersViewModel diinisialisasi dengan benar dan memegang instance Supabase
        val readAllClass = UsersViewModel().supabase.from("quiz_questions")
            .select()
            .decodeList<Quiz>() // Ambil dan decode daftar objek Quiz
        readAllClass // Kembalikan daftar yang diambil
    } catch (e: Exception) {
        Log.e("ReadClassError", "Error: ${e.message}", e) // Log error dengan stack trace
        emptyList() // Kembalikan daftar kosong jika terjadi error
    }
}



// Tampilan soal dengan opsi radio button
//@Composable
//fun QuestionItem(quiz: Quiz, userResponse: ResponUser, onAnswerSelected: (String) -> Unit) {
//    // State untuk menyimpan jawaban yang dipilih dari data ResponUser
//    var selectedOption by remember { mutableStateOf(userResponse.selectedAnswer) }
//
//    Column {
//        Text(text = quiz.question)
//        quiz.options.forEach { option ->
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                RadioButton(
//                    selected = selectedOption == option,
//                    onClick = {
//                        selectedOption = option
//                        onAnswerSelected(option) // Kirim null jika selectedOption null
//                    }
//                )
//                Text(text = option)
//            }
//        }
//    }
//}

@Composable
fun CardQuiz2(quiz: Quiz, userResponse: ResponUser, onAnswerSelected: (String) -> Unit) {
    // Mengatur state untuk opsi yang dipilih
    var selectedOption by remember { mutableStateOf(userResponse.selectedAnswer) }

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Menampilkan pertanyaan dalam format HTML
            HtmlText(
                html = quiz.question_text,
                modifier = Modifier.fillMaxWidth(),
                htmlFlags = HtmlCompat.FROM_HTML_MODE_LEGACY,
                color = R.color.black, // Ubah warna sesuai kebutuhan
                style = android.R.style.TextAppearance_Material_Body1 // Gaya teks sesuai kebutuhan
            )

            // Menampilkan pilihan jawaban
            Column {
                // Menggunakan properti langsung dari objek Quiz
                listOf(quiz.option_a, quiz.option_b, quiz.option_c, quiz.option_d).forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically // Untuk menyelaraskan radio button dengan teks
                    ) {
                        RadioButton(
                            selected = selectedOption == option,
                            onClick = {
                                selectedOption = option
                                onAnswerSelected(option) // Kirim jawaban yang dipilih
                            }
                        )

                        // Menampilkan opsi di samping RadioButton
                        HtmlText(
                            html = option,
                            modifier = Modifier.weight(1f), // Mengisi ruang yang tersisa
                            htmlFlags = HtmlCompat.FROM_HTML_MODE_LEGACY,
                            color = R.color.black, // Ubah warna sesuai kebutuhan
                            style = android.R.style.TextAppearance_Material_Body1 // Gaya teks sesuai kebutuhan
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun QuizAppPreview() {
    QuizApp()
}