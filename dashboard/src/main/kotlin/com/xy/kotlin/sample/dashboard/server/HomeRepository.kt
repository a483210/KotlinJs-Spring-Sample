package com.xy.kotlin.sample.dashboard.server

import kotlinx.serialization.builtins.serializer

/**
 * ApiRepository
 *
 * @author Created by gold on 2020/10/17 16:34
 */

/**
 * welcome
 */
suspend inline fun welcome(): String {
    return get("/home", String.serializer())
}