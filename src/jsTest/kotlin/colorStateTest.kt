import csstype.Color
import io.kotest.core.spec.style.scopes.StringSpecScope
import io.kotest.matchers.shouldBe
import redux.*

val colorStateTest: suspend StringSpecScope.() -> Unit = {
    val store = createStore(
        appReducer,
        testState(),
        rEnhancer(),
    )

    store.state.colors.size shouldBe 0

    store.dispatch(SetColor(mapOf(5 to Color("#FF8C00")))) //Orange

    store.state.colors.size shouldBe 1

    store.state.colors[5] shouldBe Color("#FF8C00")
}