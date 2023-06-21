package component

import SetGrade
import data.Course
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.InputType
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.td
import react.redux.useDispatch
import react.redux.useSelector
import redux.AppState
import redux.RAction

external interface GradeProps : Props {
    var grade: Int
    var name: String
    var index: Int
    var course: Course
}

val CGrade = FC<GradeProps> { props ->
    val dispatch = useDispatch<RAction, Unit>()
    val colors = useSelector { state: AppState -> state.colors }
    for (i in 2..5) {
        input {
            type = InputType.radio
            value = "$i"
            name = props.name
            if (i == props.grade) {
                this.checked = true
            }
            onChange = { event ->
                dispatch(SetGrade(props.index, props.course.id, event.target.let { value as String }.toInt()))
            }
            id = "${props.name}$i"
        }
    }
    label {
        css {
            color = colors[props.grade]
        }
        +"${props.grade}"
        id = "${props.name}Grade"
    }
}