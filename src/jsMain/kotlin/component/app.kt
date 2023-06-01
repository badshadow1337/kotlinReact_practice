package component

import react.FC
import react.Props
import react.create
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.details
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.summary
import react.redux.useSelector
import react.router.Route
import react.router.Routes
import react.router.dom.HashRouter
import redux.AppState


val app = FC<Props>("App") {
    val state = useSelector { state: AppState -> state }
    CModePicker { }
    CColorPicker { }
    HashRouter {
        CMenuList {
            name = "Course"
            elems = state.courses.map { it.id }.toTypedArray()
        }
        CMenuList {
            name = "Student"
            elems = state.students.map { it.id }.toTypedArray()
        }
        h3 {+"Add"}
        details {
            summary { +"Student" }
            CAddStudent { }
        }
        details {
            summary { +"Course" }
            CAddCourse { }
        }
        Routes {
            Route {
                path = "Course/:courseId"
                element = CCourse.create {}
            }
            Route{
                path = "Student/:studentId"
                element = CStudent.create {}
            }
        }
    }

}