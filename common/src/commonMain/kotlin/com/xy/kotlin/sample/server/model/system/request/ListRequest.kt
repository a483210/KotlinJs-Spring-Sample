package com.xy.kotlin.sample.server.model.system.request

import kotlinx.serialization.Serializable

/**
 * ListRequest
 *
 * @author Created by gold on 2020/10/14 18:11
 */
@Serializable
data class ListRequest(
        val pageIndex: Long = 1L,
        val pageSize: Long = 20L
)