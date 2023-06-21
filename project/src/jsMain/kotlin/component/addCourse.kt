package component

import AddCourse
import data.Course
import data.Student
import org.w3c.dom.HTMLInputElement
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.redux.useDispatch
import react.useRef
import redux.RAction

val CAddCourse = FC<Props>("AddCourse") {
    val dispatch = useDispatch<RAction, Unit>()
    val nameRef = useRef<HTMLInputElement>()
    div {
        label { +"name" }
        input { ref = nameRef }
        button {
            +"Add"
            onClick = {
                nameRef.current?.value?.let { name ->
                    dispatch(AddCourse(Course(name, emptyArray())))
                }
            }
        }
    }
}
