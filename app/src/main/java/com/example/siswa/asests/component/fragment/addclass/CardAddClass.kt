package com.example.siswa.asests.component.fragment.addclass

import ClassCodeResult
import Classes
import Student_Classes
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.siswa.asests.component.element.ButtonA
import com.example.siswa.asests.component.element.KolomInputTambah
import com.example.siswa.asests.component.element.TextD
import com.example.siswa.asests.db.UsersViewModel
import getDataId
import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddClass (navController: NavController, modifier: Modifier = Modifier, context: Context = LocalContext.current){
    val usernameState = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    Column (
        modifier = modifier
    ){
        TextD(textRes = "Kode Kelas", size = 16, modifier = Modifier)
        Spacer(modifier = Modifier.height(7.dp))
        KolomInputTambah(value = usernameState.value, onValueChange = {usernameState.value = it}, label = "Masukan Kode Kelas")
        Spacer(modifier = Modifier.height(15.dp))
        ButtonA(
            onClick = {
                coroutineScope.launch {
                    Addclass(usernameState.value, context) // Panggil suspend function di dalam coroutine
                }
            },
            text = "Bergabung"
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewAdd() {
//    SiswaTheme {
//        AddClass()
//    }
//}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(SupabaseInternal::class)
suspend fun Addclass(code: String, context: Context) {
    try {
        //    val addClass = UsersViewModel().supabase.from("classes").select().decodeList<Classes>()
//            val addClassById = addClass.forEach { classItem ->
//                classItem.id
//                Log.d("maumau", "id : ${classItem.id}, Class Name: ${classItem.name}, Description: ${classItem.description}, Code: ${classItem.class_code}")
//            }
//            val addClassBy = addClass.id
        val codeClass = UsersViewModel().supabase.from("classes")
            .select(columns = Columns.list("id, class_code")) {
                filter {
                    eq("class_code", code)
                }
            }.decodeSingle<ClassCodeResult>()

        val classId = codeClass.id
        val studentId = getDataId(context)

        // Cek apakah sudah ada data student_id dan class_id di tabel student_classes
        val existingEntry = UsersViewModel().supabase.from("student_classes")
            .select(columns = Columns.list("student_id, class_id")) {
                filter {
                    eq("student_id", studentId)
                    eq("class_id", classId)
                }
            }.decodeSingleOrNull<Student_Classes>()

        if (existingEntry != null) {
            Toast.makeText(context, "Kamu sudah masuk ke kelas tersebut", Toast.LENGTH_SHORT).show()
        } else {
            val addCodeClass = Student_Classes(studentId, classId)
            UsersViewModel().supabase.from("student_classes").insert(addCodeClass)
            Toast.makeText(context, "Berhasil Bergabung Kelas", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Log.e("AddClassError", "${e.message}", e)
        Toast.makeText(context, "Kelas tidak ditemukan", Toast.LENGTH_SHORT).show()
    }
}