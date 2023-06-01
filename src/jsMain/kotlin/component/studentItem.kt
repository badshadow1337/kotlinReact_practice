package component

import data.Mode
import data.Student
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.redux.useSelector
import redux.AppState

external interface StudentItemProps : Props {
    var student: Student
}

val CStudentItem = FC<StudentItemProps>("StudentItem") { props ->
    val mode = useSelector { state: AppState -> state.mode }
    div {
        props.student.let {
            if (mode == Mode.Short)
                +"${it.firstname[0]}. ${it.surname}"
            else
                +"${it.firstname} ${it.surname}"
            id = "StudentItem${props.student.id}"
        }
    }
}
