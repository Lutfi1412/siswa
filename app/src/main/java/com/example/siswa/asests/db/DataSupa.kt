import kotlinx.serialization.Serializable
import java.sql.Timestamp
import java.time.ZonedDateTime
import java.util.TimeZone


@Serializable
data class LoginRequest(val username: String, val password: String)

@Serializable
data class LoginResponse(val token: String)

@Serializable
data class LoginCompare(val userId: Int){
}

@Serializable
data class DataUSer(
    val id: Int,
    val username: String?,
    val password: String?,
    val role: String,
    val name: String?
)

@Serializable
data class Student_Classes(val student_id: Int, val class_id: Int)

@Serializable
data class Classes (val id: Int, val name: String, val description: String, val link_meet: String, val link_youtube: String? = null, val class_code: String, val created_by : Int, var is_hidden: Boolean)

@Serializable
data class ClassCodeResult(
    val id: Int,
    val class_code: String
)
@Serializable
data class ResaultIdStudent(val student_id: Int, val class_id: Int)

@Serializable
data class ImgUrl(val id : Int, val url: String)

@Serializable
data class getNameTeacher(val id: Int, val username: String)

@Serializable
data class getNameQuiz(val id: Int, val title: String, val description: String, val class_id: Int)
