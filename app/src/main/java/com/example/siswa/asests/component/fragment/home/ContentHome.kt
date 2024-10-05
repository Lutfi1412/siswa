@file:Suppress("UNUSED_EXPRESSION")

package com.example.siswa.asests.component.fragment.home

import Classes
import ResaultIdStudent
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.siswa.R
import com.example.siswa.asests.component.fragment.addclass.Addclass
import com.example.siswa.asests.db.UsersViewModel
import com.example.siswa.ui.theme.Routes
import getDataId
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.launch

@Composable
fun ContentHome(scrollState: LazyListState, navController: NavController, context: Context = LocalContext.current, searchQuery: String) {
    var matchedClasses by remember { mutableStateOf<List<Classes>>(emptyList()) }
    LaunchedEffect(Unit) {
        val result = ReadClass(context)
        matchedClasses = result
    }

    val filteredClasses = matchedClasses.filter { kelas ->
        kelas.name.contains(searchQuery, ignoreCase = true) || kelas.description.contains(searchQuery, ignoreCase = true)
    }
    LazyColumn(contentPadding = PaddingValues(top = 214.dp), state = scrollState) {
        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp).padding(top = 10.dp)
            ) {
                filteredClasses.forEachIndexed { i, kelas ->
                    CardKelas(
                        textjudul = kelas.name,
                        textdecs = kelas.description,
                        painter = painterResource(id = R.drawable.anime),
                        onClick = {
                            navController.navigate(Routes.kelas + "?id=${kelas.id}&desc=${kelas.description}&link_meet=${kelas.link_meet}&created_by=${kelas.created_by}&link_youtube=${kelas.link_youtube}") // Navigasi saat Card ditekan
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

suspend fun ReadClass(context: Context): List<Classes> {
    return try {
        // Ambil semua data kelas dari tabel "classes"
        val readAllClass = UsersViewModel().supabase.from("classes")
            .select().decodeList<Classes>()

        // Ambil semua data "student_classes" berdasarkan student_id
        val readIdStudentClass = UsersViewModel().supabase.from("student_classes")
            .select(columns = Columns.list("student_id, class_id")) {
                filter {
                    eq("student_id", getDataId(context))
                }
            }.decodeList<ResaultIdStudent>()

        // Simpan data yang cocok
        val matchedClasses = mutableListOf<Classes>()

        // Cek apakah class_id di "student_classes" cocok dengan id di "classes"
        readIdStudentClass.forEach { studentClass ->
            readAllClass.forEach { classItem ->
                if (studentClass.class_id == classItem.id) {
                    matchedClasses.add(classItem)
                }
            }
        }

        Log.d("MatchedClasses", "Kelas yang cocok: $matchedClasses")
        matchedClasses // Mengembalikan hasil yang cocok

    } catch (e: Exception) {
        Log.d("ReadClassError", "Error: ${e.message}")
        emptyList()
    }
}
