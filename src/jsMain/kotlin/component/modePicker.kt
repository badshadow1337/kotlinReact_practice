package component

import ChangeMode
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

val CModePicker = FC<Props>("ModePicker") {
    val mode = useSelector {state: AppState -> state.mode.name  }
    val dispatch = useDispatch<RAction, Unit>()
    val modes = Mode.values().map { it.name }
    val refs = modes.map {
        useRef<HTMLInputElement>()
    }
    val action: MouseEventHandler<*> = {
        refs
            .find { it.current?.checked ?: false }
            ?.current?.value
            ?.let {
                if (it != mode)
                    dispatch(ChangeMode())
            }
    }
    h3 { +"Output Mode" }
    modes.mapIndexed { index, _mode ->
        span {
            input {
                type = InputType.radio
                value = _mode
                ref = refs[index]
                onClick = action
                name = "outputMode"
                if (mode == _mode)
                    defaultChecked = true
                id = _mode
            }
            +_mode
        }
    }
}