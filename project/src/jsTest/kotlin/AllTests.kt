import io.kotest.core.spec.style.StringSpec

class AllTests : StringSpec({
    "StudentItem test"(studentItemTest)

    "State test"(stateTest)

    "Grade test"(gradeTest)

    "Grade State Test"(gradeStateTest)

    "Color Test"(colorTest)

    "Color State Test"(colorStateTest)

    "Color State Test"(ActionTest)
})

//:jsTest "AllTests"