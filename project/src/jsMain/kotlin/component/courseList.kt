package component

import csstype.FontWeight
import data.Course
import data.CourseId
import data.Student
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol

external interface CourseListProps : Props {
    var courses: Array<Course>
    var marked: Array<Boolean>
    var clickId: (CourseId) -> Unit
    var student: Student
}

val CCourseList = FC<CourseListProps>("CourseList") {props ->
    ol {
        props.courses.forEachIndexed { index, course ->
            li {
                val indexStudent = course.students.indexOfFirst { it == props.student.id }
                +course.id
                id = "CourseItem${course.id}"
                if (props.marked[index])
                    css {
                        fontWeight = FontWeight.bold
                    }
                onClick = {
                    props.clickId(course.id)
                }
                CGrade {
                    name = "${props.student.id}${course.id}$index"
                    grade = course.grades[indexStudent]
                    this.index = indexStudent
                    this.course = course
                }
            }
        }
    }
}