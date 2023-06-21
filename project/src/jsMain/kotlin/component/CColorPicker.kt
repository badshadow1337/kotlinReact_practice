package component

import ChangeMode
import SetColor
import csstype.Color
import data.Mode
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.events.MouseEventHandler
import react.dom.html.InputType
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.span
import react.redux.useDispatch
import react.redux.useSelector
import redux.AppState
import redux.RAction

val CColorPicker = FC<Props>("ModePicker") {
    val dispatch = useDispatch<RAction, Unit>()
    val ref = useRef<HTMLInputElement>()
    val ref2 = useRef<HTMLInputElement>()
    h3 { +"Color Mode" }
    input {
        type = InputType.number
        this.ref = ref
        id = "InputChoiceGrade"
        onClick = {
            dispatch(SetColor(mapOf(ref.current!!.value.toInt() to Color(ref2.current!!.value))))
        }
    }
    input {
        type = InputType.color
        this.ref = ref2
        onChange = {
            dispatch(SetColor(mapOf(ref.current!!.value.toInt() to Color(ref2.current!!.value))))
        }
        id = "InputChoiceColor"
    }
}