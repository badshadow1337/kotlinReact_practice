package component

import AddStudentToCourse
import DeleteCourses
import MarkStudent
import SelectedCourse
import data.StudentId
import kotlinx.js.Record
import org.w3c.dom.HTMLSelectElement
import react.FC
import react.Props
import react.dom.html.InputType
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.details
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select
import react.dom.html.ReactHTML.span
import react.dom.html.ReactHTML.summary
import react.redux.useDispatch
import react.redux.useSelector
import react.router.useParams
import react.useRef
import redux.AppState
import redux.RAction

val CStudent = FC<Props>("Student") {
    val params: Record<String, String> = useParams()
    val studentId = params["studentId"] as StudentId
    val student = useSelector { state: AppState -> state.getStudentsById(arrayOf(studentId)) }.first()
    val courses = useSelector { state: AppState -> state.getCourses(student) }
    val allCourses = useSelector { state: AppState -> state.courses }
    val candidates = allCourses.toList() - courses.mapTo(hashSetOf()){it.first}
    val dispatch = useDispatch<RAction, Unit>()
    val addCourseSelectRef = useRef<HTMLSelectElement>()
    div {
        h3 { +student.id }
        details {
            summary { +"Add Course" }
            select {
                ref = addCourseSelectRef
                candidates.forEach {
                    option { +it.id }
                }
            }
            button {
                +"Add"
                onClick = {
                    addCourseSelectRef.current?.value?.let {
                        dispatch(AddStudentToCourse(it, studentId))
                    }
                }
            }
        }
        details {
            summary { +"Delete Courses" }
            id = "DetailsDeleteCourses"
            courses.forEachIndexed { index, (course , _) ->
                span {
                    +course.id
                    input {
                        id = "${course.id}CheckBox$index"
                        type = InputType.checkbox
                        if (student.selectCourses.firstOrNull { it == course.id } != null) {
                            this.checked = true
                        }
                        onClick = {
                            dispatch(SelectedCourse(studentId, course.id))
                        }
                    }
                }
            }
            button {
                +"Delete"
                id = "ButtonDeleteCourses"
                onClick = {
                    dispatch(DeleteCourses(studentId, student.selectCourses))
                    student.selectCourses = emptyArray()
                }
            }
        }

        label {
            +student.selectCourses.toList().toString()
        }

        CCourseList {
            this.student = student
            this.courses = courses.map { it.first }.toTypedArray()
            this.marked = courses.map { it.second }.toTypedArray()
            this.clickId = { dispatch(MarkStudent(it, studentId)) }
        }
    }
}
