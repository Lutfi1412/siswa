package com.example.siswa.asests.component.layout

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.siswa.R
import com.example.siswa.asests.component.element.TextC
import com.example.siswa.asests.component.element.TextD
import com.example.siswa.asests.component.fragment.BackBtn
import com.example.siswa.asests.component.fragment.home.CardQuizGuru
import com.example.siswa.asests.component.fragment.kelas.BtnGuru
import com.example.siswa.asests.component.fragment.kelas.ProfileGuru
import com.example.siswa.asests.db.UsersViewModel
import com.example.siswa.ui.theme.Routes
import getNameQuiz
import getNameTeacher
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

@SuppressLint("RememberReturnType")
@Composable
fun KelasLayout(
    items: List<String>,
    painter: Painter,
    id: Int,
    desc: String,
    linkMeet: String,
    createdBy: Int,
    link_youtube: String?,
    painter2: Painter,
    navController: NavController
) {
    val fromProfile = false
    val context = LocalContext.current

    // State untuk menyimpan nama teacher
    var teacherName by remember { mutableStateOf("") }

    var quizList by remember { mutableStateOf<List<getNameQuiz>>(emptyList()) } // State untuk menyimpan daftar kuis

    // Mengambil nama teacher dan daftar kuis berdasarkan ID kelas
    LaunchedEffect(createdBy) {
        teacherName = getNameTeacher(createdBy)
        quizList = getNameQuiz(id) // Mengambil daftar kuis berdasarkan ID kelas
        Log.d("KelasLayoutjay", "Fetched quizzes: ${quizList.size} items")
    }

    val intent = remember {
        if (linkMeet.isNotEmpty()) {
            Intent(Intent.ACTION_VIEW, Uri.parse(linkMeet))
        } else {
            null
        }
    }

    val intent2 = remember {
        if (!link_youtube.isNullOrEmpty()) {
            Intent(Intent.ACTION_VIEW, Uri.parse(link_youtube))
        } else {
            null
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 5.dp)
    ) {
        BackBtn(text = "Course Detail", modifier = Modifier, onclik = { navController.navigate("${Routes.dashboard}/${fromProfile}") })

        LazyColumn(
            modifier = Modifier.fillMaxHeight().padding(horizontal = 20.dp),
        ) {
            item {
                TextD(textRes = "Course Detail", size = 22, modifier = Modifier)
            }
            item {
                Spacer(modifier = Modifier.height(25.dp))
            }

            // Menampilkan ProfileGuru dengan nama teacher yang telah didapatkan
            item {
                ProfileGuru(
                    textjudul = teacherName, // Menampilkan nama teacher
                    painter = painter2,
                    modifier = Modifier
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Menampilkan TextC
            item {
                TextC(
                    textRes = desc,
                    size = 14,
                    modifier = Modifier,
                    colorHex = "#0a0a0a"
                )
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Menampilkan BtnGuru untuk Google Meet dan YouTube
            item {
                BtnGuru(
                    modifier = Modifier,
                    onclik = { intent?.let { context.startActivity(it) } },
                    onclik2 = {
                        if (intent2 != null) {
                            context.startActivity(intent2)
                        } else {
                            Log.d("DebugIntent", "Intent2 is null or linkYoutobe is invalid")
                        }
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(15.dp))
            }

            // Menampilkan TextD kedua
            item {
                TextD(textRes = "Quizzes", size = 22, modifier = Modifier)
            }
            item {
                Spacer(modifier = Modifier.height(5.dp))
            }

            // Menampilkan daftar kuis dengan LazyColumn
            items(quizList) { quiz -> // Mengiterasi list kuis
                CardQuizGuru(
                    textjudul = quiz.title,
                    textdecs = quiz.description,
                    painter = painter,
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    onclick = {
                        navController.navigate(Routes.instruksi)
                    }
                )
            }
        }
    }
}

suspend fun getNameTeacher(createdBy: Int): String {
    return try {
        val result = UsersViewModel().supabase.from("users")
            .select(columns = Columns.list("id", "username")) {
                filter { eq("id", createdBy) }
            }
            .decodeSingle<getNameTeacher>() // Ganti Map<String, Any> dengan User
        result.username // Mengambil username
    } catch (e: Exception) {
        Log.e("getNameTeacherError", "Error fetching teacher name: ${e.message}")
        ""
    }
}

suspend fun getNameQuiz(id: Int): List<getNameQuiz> {
    return try {
        UsersViewModel().supabase.from("quizzes") // Pastikan tabel yang diambil sesuai, misalnya 'quiz'
            .select(columns = Columns.list("id", "title", "description", "class_id")) {
                filter { eq("class_id", id) }
            }.decodeList<getNameQuiz>() // Mendecode sebagai list dari Quiz
    } catch (e: Exception) {
        Log.e("getNameQuizError", "Error fetching Quiz: ${e.message}")
        emptyList() // Return empty list jika ada error
    }
}



//@Preview(showBackground = true)
//@Composable
//fun PreviewKelas() {
//    SiswaTheme {
//        KelasLayout(
//            remember { List(10) { "Item $it" } }, painterResource(id = R.drawable.anime), "hsjhjw", "ajsja", "ajjsajsjak",
//            painterResource(
//            id = R.drawable.anime,
//        ),
//            navController = NavController(LocalContext.current))
//    }
//}