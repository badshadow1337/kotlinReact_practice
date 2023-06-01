package data

typealias StudentId = String

data class Student(
    val firstname: String,
    val surname: String,
    var selectCourses: Array<CourseId> = emptyArray()
) {
    val id: StudentId
        get() = "$firstname $surname"
}

val studentList =
    arrayOf(
        Student("Iziuel", "Bode"),
        Student("Rosa", "Gurwitsch"),
        Student("Sigmundur", "Thue")
    )