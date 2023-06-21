import component.CStudentItem
import io.kotest.core.spec.style.scopes.StringSpecScope
import io.kotest.matchers.shouldBe
import kotlinx.browser.document
import kotlinx.coroutines.delay
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.get
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.redux.Provider
import redux.*

val studentItemTest: suspend StringSpecScope.() -> Unit = {
    val store = createStore(
        appReducer,
        testState(),
        rEnhancer(),
    )

    val container = document.createElement("div")
    document.body!!.appendChild(container)
    val root = createRoot(container)

    val sheldon = store.state.getStudentsById(arrayOf("Sheldon Cooper")).first()
    val cSheldon = FC { _: Props ->
        Provider {
            this.store = store
            val comp = CStudentItem.create {
                student = sheldon
            }
            children = comp
        }
    }

    root.render(cSheldon.create())
    delay(1)
    console.log(container)
    val helloDiv = container.childNodes[0] as HTMLDivElement
    helloDiv.innerHTML shouldBe "Sheldon Cooper"

    store.dispatch(ChangeMode())
    root.render(cSheldon.create())
    delay(1)
    console.log(container)
    val helloDiv1 = container.childNodes[0] as HTMLDivElement
    helloDiv1.innerHTML shouldBe "S. Cooper"
}