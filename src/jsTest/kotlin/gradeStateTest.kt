import io.kotest.assertions.withClue
import io.kotest.core.spec.style.scopes.StringSpecScope
import io.kotest.matchers.shouldBe
import redux.*

val gradeStateTest: suspend StringSpecScope.() -> Unit = {
    val store = createStore(
        appReducer,
        testState(),
        rEnhancer(),
    )

    val math = store.state.courses.indexOfFirst { it.id == "Math" }

    val sheldon = store.state.students.indexOfFirst { it.firstname == "Sheldon" }
    val leonard = store.state.students.indexOfFirst { it.firstname == "Leonard" }
    val howard = store.state.students.indexOfFirst { it.firstname == "Howard" }

    store.dispatch(SetGrade( sheldon,store.state.courses[math].id, 5))
    store.dispatch(SetGrade( leonard,store.state.courses[math].id, 3))
    store.dispatch(SetGrade( howard,store.state.courses[math].id, 4))

    withClue("Check change grades") {
        store.state.courses[math].grades[sheldon] shouldBe 5
        store.state.courses[math].grades[leonard] shouldBe 3
        store.state.courses[math].grades[howard] shouldBe 4
    }
}