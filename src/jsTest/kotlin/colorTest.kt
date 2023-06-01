import component.CColorPicker
import component.CStudentItem
import csstype.Color
import io.kotest.core.spec.style.scopes.StringSpecScope
import io.kotest.matchers.shouldBe
import kotlinx.browser.document
import kotlinx.coroutines.delay
import org.w3c.dom.*
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.redux.Provider
import redux.*

val colorTest: suspend StringSpecScope.() -> Unit = {
    val store = createStore(
        appReducer,
        testState(),
        rEnhancer(),
    )

    val container = document.createElement("div")
    document.body!!.appendChild(container)
    val root = createRoot(container)

    val cSheldon = FC { _: Props ->
        Provider {
            this.store = store
            val comp = CColorPicker.create {}
            children = comp
        }
    }

    root.render(cSheldon.create())
    delay(1)
    console.log(container)

    val h3 = container.childNodes[0] as HTMLHeadingElement
    val inputNumber = container.childNodes[1] as HTMLInputElement
    val inputColor = container.childNodes[2] as HTMLInputElement
    h3.innerHTML shouldBe "Color Mode"
    inputNumber.type shouldBe "number"
    inputColor.type shouldBe "color"
//    select.childNodes[0]!!.textContent shouldBe "1"
//    select.childNodes[1]!!.textContent shouldBe "2"
//    select.childNodes[2]!!.textContent shouldBe "3"
//    select.childNodes[3]!!.textContent shouldBe "4"
//    select.childNodes[4]!!.textContent shouldBe "5"

//    val input = container.childNodes[1] as HTMLInputElement
}