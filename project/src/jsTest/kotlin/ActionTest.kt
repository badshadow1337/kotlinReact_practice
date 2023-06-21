import io.kotest.assertions.withClue
import io.kotest.core.spec.style.scopes.StringSpecScope
import io.kotest.matchers.shouldBe
import redux.*

val ActionTest: suspend StringSpecScope.() -> Unit = {
    val store = createStore(
        appReducer,
        testState(),
        rEnhancer()
    )

        // Удалить студента с нескольких курсов.

    val math = store.state.courses.firstOrNull {it.id == "Math"}
    val phys = store.state.courses.firstOrNull {it.id == "Phys"}

    check(math!=null)
    check(phys!=null)

    withClue("Провека количества студентов на курсах") {
        math.students.size shouldBe 3
        phys.students.size shouldBe 3
    }

    val sheldon = store.state.students.firstOrNull { it.firstname == "Sheldon" }

    check(sheldon!=null)
    store.dispatch(DeleteCourses(sheldon.id, arrayOf(math.id, phys.id)))

    val math1 = store.state.courses.firstOrNull {it.id == "Math"}
    val phys1 = store.state.courses.firstOrNull {it.id == "Phys"}

    check(math1!=null)
    check(phys1!=null)

    withClue("Провека количества студентов на курсах") {
        math1.students.size shouldBe 2
        phys1.students.size shouldBe 2
    }
}