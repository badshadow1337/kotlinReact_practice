package data

import csstype.array

typealias CourseId = String

data class Course(
    val name: String,
    val students: Array<StudentId>,
    val marked: Array<Boolean> = Array(students.size) { false },
    val grades: Array<Int> = Array(students.size) { 2 }
) {
    val id: CourseId
        get() = name
}

val courseList =
    arrayOf(
        "Math",
        "Phys",
        "Story"
    ).map {
        Course(it, studentList.map { it.id }.toTypedArray())
    }