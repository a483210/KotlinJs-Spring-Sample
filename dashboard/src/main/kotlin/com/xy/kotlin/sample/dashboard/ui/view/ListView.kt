package com.xy.kotlin.sample.dashboard.ui.view

import kotlinx.css.*
import kotlinx.html.DIV
import kotlinx.html.js.onClickFunction
import materialui.components.circularprogress.circularProgress
import materialui.components.icon.icon
import materialui.components.list.list
import materialui.components.listitem.buttonListItem
import materialui.components.listitemicon.listItemIcon
import materialui.components.listitemtext.listItemText
import materialui.components.radio.radio
import react.*
import react.dom.span
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

/**
 * ListView
 *
 * @author Created by gold on 2020/10/20 11:41
 */
class ListView : RComponent<ListViewProps, ListViewState>() {

    override fun ListViewState.init() {
        loading = true
        selected = -1
    }

    override fun componentWillReceiveProps(nextProps: ListViewProps) {
        if (state.loading && nextProps.list.isNotEmpty()) {
            state.loading = false
        }

        state.selected = -1
    }

    override fun RBuilder.render() {
        if (state.loading) {
            centerDiv {
                circularProgress { }
            }
        } else {
            if (props.list.isEmpty()) {
                centerDiv {
                    icon {
                        +"hourglass_empty_icon"
                    }
                }

                centerDiv {
                    css {
                        marginTop = 20.px
                    }

                    span {
                        +"当前没有选项"
                    }
                }
            } else {
                childList()
            }
        }
    }

    private fun RBuilder.centerDiv(block: StyledDOMBuilder<DIV>.() -> Unit) {
        styledDiv {
            css {
                display = Display.flex
                justifyContent = JustifyContent.center
            }

            block()
        }
    }

    private fun RBuilder.childList() {
        list {
            for (i in props.list.indices) {
                val item = props.list[i]

                buttonListItem {
                    attrs {
                        onClickFunction = {
                            setState {
                                selected = i
                            }
                        }
                    }

                    listItemIcon {
                        icon {
                            +"menu_icon"
                        }
                    }

                    listItemText {
                        +item
                    }
                    radio {
                        attrs {
                            checked = state.selected == i
                        }
                    }
                }
            }
        }
    }

}

external interface ListViewProps : RProps {

    var list: List<String>

}

external interface ListViewState : RState {

    var loading: Boolean
    var selected: Int

}

fun RBuilder.listView(handler: ListViewProps.() -> Unit): ReactElement {
    return child(ListView::class) {
        this.attrs(handler)
    }
}