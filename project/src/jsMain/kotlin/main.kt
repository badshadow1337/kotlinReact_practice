import component.app
import kotlinx.browser.document
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.redux.Provider
import redux.*

fun main() {
    val container = document.createElement("div")
    document.body!!.appendChild(container)
    createRoot(container).render(appStore.create())
}

val appStore = FC<Props>("main") {
    Provider {
        store = createStore(
            appReducer,
            testState(),
            try {
                compose(
                    rEnhancer(),
                    js("window.__REDUX_DEVTOOLS_EXTENSION__()")
                )
            } catch (_: Throwable) {
                rEnhancer()
            }
        )
        children = app.create()
    }
}