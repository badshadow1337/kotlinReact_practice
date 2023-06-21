import csstype.Color
import data.Course
import data.CourseId
import data.Student
import data.StudentId
import redux.RAction

open class CourseAction: RAction
open class StudentAction: RAction
open class ModeAction: RAction
open class ColorAction: RAction

class MarkStudent(
    val courseId: CourseId,
    val studentId: StudentId
) : CourseAction()

class AddStudent(
    val student: Student
): StudentAction()

class AddCourse(
    val course: Course
): CourseAction()

class AddStudentToCourse(
    val courseId: CourseId,
    val studentId: StudentId
): CourseAction()

class SelectedCourse(
    val studentId: StudentId,
    val courseId: CourseId
): StudentAction()

class DeleteCourses(
    val studentId: StudentId,
    val courses: Array<CourseId>
): CourseAction()

class SetGrade(
    val indexStudent: Int,
    val courseId: CourseId,
    val grade: Int
): CourseAction()

class SetColor(
    val color: Map<Int,Color>
): ColorAction()

class ChangeMode() : ModeAction()