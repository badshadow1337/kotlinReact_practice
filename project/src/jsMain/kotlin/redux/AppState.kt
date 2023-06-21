package redux

import csstype.Color
import data.*

typealias CourseState = Array<Course>
typealias StudentState = Array<Student>
typealias ModeState = Mode
typealias ColorState = Map<Int, Color>

class AppState(
    val courses: CourseState = emptyArray(),
    val students: StudentState = emptyArray(),
    val mode: Mode = Mode.Full,
    val colors: Map<Int, Color> = mapOf()
) {
    fun getStudentsById(studentIds: Array<StudentId>) =
        studentIds.mapNotNull { studentId ->
            students.firstOrNull {
                it.id == studentId
            }
        }

    fun getCoursesById(courseIds: Array<CourseId>) =
        courseIds.mapNotNull { courseId ->
            courses.firstOrNull {
                it.id == courseId
            }
        }

    fun getCourses(student: Student): List<Pair<Course, Boolean>> =
        courses
            .filter { it.students.contains(student.id) }
            .map {
                it to it.marked[it.students.indexOf(student.id)]
            }
}

fun testState() =
    AppState(
        courseList.map { it }.toTypedArray(),
        studentList.map { it }.toTypedArray(),
    )