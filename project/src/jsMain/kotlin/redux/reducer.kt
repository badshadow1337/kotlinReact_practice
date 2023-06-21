package redux

import AddCourse
import AddStudent
import AddStudentToCourse
import ChangeMode
import ColorAction
import CourseAction
import DeleteCourses
import MarkStudent
import ModeAction
import SelectedCourse
import SetColor
import SetGrade
import StudentAction
import react.Reducer
import react.router.matchRoutes
import tools.minus
import tools.plus
import tools.replace

val appReducer: Reducer<AppState, RAction> =
    { state, action ->
        when (action) {
            is CourseAction -> AppState(
                courseReducer(state.courses, action),
                state.students,
                state.mode,
                state.colors
            )

            is StudentAction -> AppState(
                state.courses,
                studentReducer(state.students, action),
                state.mode,
                state.colors
            )

            is ModeAction -> AppState(
                state.courses,
                state.students,
                modeReducer(state.mode, action),
                state.colors
            )

            is ColorAction -> AppState(
                state.courses,
                state.students,
                state.mode,
                colorReducer(state.colors, action)
            )

            else -> state
        }
    }

val courseReducer: Reducer<CourseState, CourseAction> =
    { state, action ->
        when (action) {
            is MarkStudent -> state.replace(
                { _, course -> course.id == action.courseId },
                { course ->
                    course.copy(
                        marked = course.marked.replace(
                            { index, _ -> course.students[index] == action.studentId },
                            { mark -> !mark }
                        )
                    )
                }
            )

            is AddCourse -> state + action.course

            is AddStudentToCourse -> state.replace(
                { _, course -> course.id == action.courseId },
                { course ->
                    course.copy(
                        students = course.students + action.studentId,
                        marked = course.marked + false,
                        grades = course.grades + 2
                    )
                }
            )

            is SetGrade -> Array(state.size) { index->
                val course = state[index]
                if (course.id == action.courseId){
                    course.grades[action.indexStudent] = action.grade
                    course
                } else {
                    course
                }
            }

            is DeleteCourses ->  Array(state.size) {index->
                val course = state[index]
                if (course.id in action.courses) {
                    course.copy(
                        students = course.students - action.studentId,
                        marked = Array(course.marked.size) {
                            if (it != course.students.indexOf(action.studentId)) {
                                course.marked[it]
                            } else {
                                null
                            }
                        }.filterNotNull().toTypedArray(),
                        grades = Array(course.grades.size) {
                            if (it != course.students.indexOf(action.studentId)) {
                                course.grades[it]
                            } else {
                                null
                            }
                        }.filterNotNull().toTypedArray()
                    )
                } else {
                    course
                }
            }


            else -> state
        }
    }

val studentReducer: Reducer<StudentState, StudentAction> =
    { state, action ->
        when (action) {
            is AddStudent -> state + action.student
            is SelectedCourse -> Array(state.size) { index ->
                val student = state[index]
                if (student.id == action.studentId) {
                    val courseCheck = student.selectCourses.firstOrNull { courseId -> courseId == action.courseId }
                    if (courseCheck != null) student.copy(
                        selectCourses = student.selectCourses - action.courseId
                    )
                    else student.copy(
                        selectCourses = student.selectCourses + action.courseId
                    )
                } else {
                    student
                }
            }
            else -> state
        }
    }

val modeReducer: Reducer<ModeState, ModeAction> =
    { state, action ->
        when (action) {
            is ChangeMode -> !state
            else -> state
        }
    }

val colorReducer: Reducer<ColorState, ColorAction> =
    { state, action ->
        when (action) {
            is SetColor -> state + action.color
            else -> state
        }
    }


