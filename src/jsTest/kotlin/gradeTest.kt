import component.CGrade
import component.CStudentItem
import io.kotest.core.spec.style.scopes.StringSpecScope
import io.kotest.matchers.shouldBe
import kotlinx.browser.document
import kotlinx.coroutines.delay
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLLabelElement
import org.w3c.dom.get
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.redux.Provider
import redux.*

val gradeTest: suspend StringSpecScope.() -> Unit = {
    val store = createStore(
        appReducer,
        testState(),
        rEnhancer(),
    )

    val container = document.createElement("div")
    document.body!!.appendChild(container)
    val root = createRoot(container)

    val math = store.state.courses.indexOfFirst { it.id == "Math" }
    val sheldon = store.state.courses[math].students.indexOfFirst { it == "Sheldon Cooper" }

    val cSheldon = FC { _: Props ->
        Provider {
            this.store = store
            val comp = CGrade.create {
                grade = store.state.courses[math].grades[sheldon]
                index = store.state.courses[math].students.indexOfFirst { it == "Sheldon Cooper" }
                course = store.state.courses[math]
                name = "MathSheldonCooper"
            }
            children = comp
        }
    }

    root.render(cSheldon.create())
    delay(1)
    console.log(container)

    val inputRadio2 = container.childNodes[0] as HTMLInputElement
    val inputRadio3 = container.childNodes[1] as HTMLInputElement
    val inputRadio4 = container.childNodes[2] as HTMLInputElement
    val inputRadio5 = container.childNodes[3] as HTMLInputElement
    val labelGrade = container.childNodes[4] as HTMLLabelElement

    inputRadio2.value shouldBe "2"
    inputRadio2.checked shouldBe true
    inputRadio3.value shouldBe "3"
    inputRadio3.checked shouldBe false
    inputRadio4.value shouldBe "4"
    inputRadio4.checked shouldBe false
    inputRadio5.value shouldBe "5"
    inputRadio5.checked shouldBe false
    labelGrade.textContent shouldBe "2"

    store.dispatch(SetGrade(sheldon, store.state.courses[math].id, 3))

    root.render(cSheldon.create())
    delay(1)
    console.log(container)

    val inputRadio21 = container.childNodes[0] as HTMLInputElement
    val inputRadio31 = container.childNodes[1] as HTMLInputElement
    val inputRadio41 = container.childNodes[2] as HTMLInputElement
    val inputRadio51 = container.childNodes[3] as HTMLInputElement
    val labelGrade1 = container.childNodes[4] as HTMLLabelElement

    inputRadio21.value shouldBe "2"
    inputRadio21.checked shouldBe false
    inputRadio31.value shouldBe "3"
    inputRadio31.checked shouldBe true
    inputRadio41.value shouldBe "4"
    inputRadio41.checked shouldBe false
    inputRadio51.value shouldBe "5"
    inputRadio51.checked shouldBe false
    labelGrade1.textContent shouldBe "3"
}