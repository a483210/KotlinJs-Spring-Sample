package com.xy.kotlin.sample.dashboard.server

import com.xy.kotlin.sample.server.model.request.SetItemsRequest
import com.xy.kotlin.sample.server.model.system.result.ListResult
import com.xy.kotlin.sample.server.model.system.result.NullResult
import kotlinx.serialization.builtins.serializer

/**
 * ApiRepository
 *
 * @author Created by gold on 2020/10/17 16:34
 */

/**
 * 获取列表
 */
suspend inline fun homeList(): ListResult<String> {
    return getList("/home/items", String.serializer())
}

/**
 * 设置列表
 * @param list 列表
 */
suspend inline fun setItems(list: List<String>): NullResult {
    return post("/home/items", NullResult.serializer()) {
        body = SetItemsRequest(list)
    }
}

/**
 * 添加项目
 * @param item 项目
 */
suspend inline fun addItem(item: String): NullResult {
    return put("/home/items?item=$item", NullResult.serializer())
}

/**
 * 删除项目
 * @param item 项目
 */
suspend inline fun removeItem(item: String): NullResult {
    return delete("/home/items?item=$item", NullResult.serializer())
}