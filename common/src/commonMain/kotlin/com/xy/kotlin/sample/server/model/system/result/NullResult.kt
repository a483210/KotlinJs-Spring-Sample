package com.xy.kotlin.sample.server.model.system.result

import kotlinx.serialization.Serializable

/**
 * 空数据返回
 *
 * @author Created by gold on 2020/10/14 16:47
 */
@Serializable
data class NullResult(val content: String? = null) {
}