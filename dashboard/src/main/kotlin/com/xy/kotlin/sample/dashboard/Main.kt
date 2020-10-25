package com.xy.kotlin.sample.dashboard

import com.xy.kotlin.sample.dashboard.server.welcome
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.dom.h1
import react.dom.render
import styled.css
import styled.styledButton
import styled.styledDiv
import styled.styledH1

/**
 *  主入口
 *
 * @author Created by gold on 2020/10/16 16:11
 */
fun main() {
    render(document.getElementById("root")) {
        styledH1 {
            css {
                justifyContent = JustifyContent.center
                display = Display.flex
            }

            +"Hello Kotlin/Js"
        }

        styledDiv {
            css {
                justifyContent = JustifyContent.center
                display = Display.flex

                marginTop = 100.px
            }

            styledButton {
                css {
                    width = 120.px
                    height = 60.px
                }

                attrs {
                    onClickFunction = {
                        MainScope().launch {
                            val result = welcome()

                            window.alert("请求结果：$result")
                        }
                    }
                }

                +"发起请求"
            }
        }
    }
}