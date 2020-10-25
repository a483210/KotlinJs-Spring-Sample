package com.xy.kotlin.sample.dashboard.ui.page

import com.xy.kotlin.sample.dashboard.server.addItem
import com.xy.kotlin.sample.dashboard.server.homeList
import com.xy.kotlin.sample.dashboard.server.removeItem
import com.xy.kotlin.sample.dashboard.server.setItems
import com.xy.kotlin.sample.dashboard.ui.view.ListView
import com.xy.kotlin.sample.dashboard.ui.view.listView
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.html.BUTTON
import kotlinx.html.js.onClickFunction
import materialui.components.button.ButtonElementBuilder
import materialui.components.button.button
import materialui.components.button.enums.ButtonVariant
import materialui.components.grid.enums.GridJustify
import materialui.components.grid.grid
import org.w3c.dom.events.Event
import react.*
import styled.css
import styled.styledDiv
import styled.styledH1

/**
 * HomePage
 *
 * @author Created by gold on 2020/10/19 19:42
 */

class HomePage : RComponent<RProps, HomePageState>() {

    override fun HomePageState.init() {
        list = mutableListOf()
    }

    override fun componentDidMount() {
        MainScope().launch {
            val result = homeList()

            setState {
                list = result.list.toMutableList()
            }
        }
    }

    override fun RBuilder.render() {
        header()

        center()

        footer()
    }

    private fun RBuilder.header() {
        styledH1 {
            css.textAlign = TextAlign.center

            +"Kotlin/Js Sample Dashboard"
        }
    }

    private fun RBuilder.center() {
        styledDiv {
            css {
                paddingTop = 32.px
                paddingBottom = 32.px
            }

            listView {
                ref<ListView> {
                    state.listRef = it
                }

                list = state.list
            }
        }
    }

    private fun RBuilder.footer() {
        grid {
            setProp("spacing", 2)

            attrs {
                container = true
                justify = GridJustify.center
            }

            childButton {
                attrs {
                    onClickFunction = onClickSetItem()
                }

                +"重置选项"
            }

            childButton {
                attrs {
                    onClickFunction = onClickAddItem()
                }

                +"添加选项"
            }

            childButton {
                attrs {
                    onClickFunction = onClickRemoveItem()
                }

                +"删除选项"
            }
        }
    }

    private fun onClickSetItem(): (Event) -> Unit = {
        MainScope().launch {
            val items = listOf("选项1", "选项2", "选项3")

            setItems(items)

            setState {
                list.clear()
                list.addAll(items)
            }
        }
    }

    private fun onClickAddItem(): (Event) -> Unit = {
        MainScope().launch {
            val index = when (state.list.isEmpty()) {
                true -> 0
                false -> {
                    val lastItem = state.list[state.list.size - 1]

                    val values = lastItem.match("(\\d+)(?!.*\\d)")
                    if (values == null || values.isEmpty()) {
                        throw IllegalArgumentException("无法获取最后的数值")
                    }

                    values[values.size - 1].toInt()
                }
            }

            val item = "选项${index + 1}"

            addItem(item)

            setState {
                list.add(item)
            }
        }
    }

    private fun onClickRemoveItem(): (Event) -> Unit = {
        val selected = state.listRef?.state?.selected ?: -1

        if (selected == -1) {
            window.alert("未选择选项")
        } else {
            val item = state.listRef?.props?.list?.get(selected)
            if (item == null) {
                window.alert("未选择选项")
            } else {
                MainScope().launch {
                    removeItem(item)

                    setState {
                        list.removeAt(selected)
                    }
                }
            }
        }
    }

    private fun RBuilder.childButton(block: ButtonElementBuilder<BUTTON>.() -> Unit) {
        grid {
            attrs.item = true

            button {
                attrs {
                    variant = ButtonVariant.contained
                }

                block()
            }
        }
    }
}

external interface HomePageState : RState {

    var list: MutableList<String>
    var listRef: ListView?

}