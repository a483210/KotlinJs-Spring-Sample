package com.xy.kotlin.sample.server.model.request

import kotlinx.serialization.Serializable

/**
 * SetItemsRequest
 *
 * @author Created by gold on 2020/10/19 15:49
 */
@Serializable
data class SetItemsRequest(val list: List<String>)