package com.xy.kotlin.sample.dashboard

import com.xy.kotlin.sample.dashboard.ui.page.HomePage
import kotlinx.browser.document
import react.dom.render

/**
 *  主入口
 *
 * @author Created by gold on 2020/10/16 16:11
 */
fun main() {
    render(document.getElementById("root")) {
        child(HomePage::class) {}
    }
}