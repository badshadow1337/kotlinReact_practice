package component

import csstype.FontWeight
import data.Course
import data.Student
import data.StudentId
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol

external interface StudentListProps : Props {
    var students: Array<Student>
    var marked: Array<Boolean>
    var clickId: (StudentId) -> Unit
    var course: Course
}

val CStudentList = FC<StudentListProps>("StudentList") {props ->
    ol {
        props.students.forEachIndexed { index, student ->
            li {
                CStudentItem {
                    this.student = student
                }
                if (props.marked[index])
                    css {
                        fontWeight = FontWeight.bold
                    }
                onClick = {
                    props.clickId(student.id)
                }
                CGrade {
                    name = "${props.course.id}${student.id}$index"
                    grade = props.course.grades[index]
                    this.index = index
                    course = props.course
                }
            }
        }
    }
}