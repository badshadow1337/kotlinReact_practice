package component

import csstype.Display
import csstype.Flex
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3
import react.router.dom.Link

external interface MenuListProps : Props {
    var name: String
    var elems: Array<String>
}

val CMenuList = FC<MenuListProps>("Menu"){ props ->
    h3 { +props.name}
    div {
        css {
            display = Display.flex
        }
        props.elems.forEach {
            Link {
                css {
                    flex = Flex.minContent
                }
                +it
                to = "${props.name}/$it"
                id = "LinkTo${props.name}$it"
            }
        }
    }
}