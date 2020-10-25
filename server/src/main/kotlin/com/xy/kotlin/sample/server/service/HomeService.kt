package com.xy.kotlin.sample.server.service

import com.xy.kotlin.sample.server.model.system.result.ListResult

/**
 * HomeService
 *
 * @author Created by gold on 2020/10/19 14:28
 */
interface HomeService {

    /**
     * 获取列表
     * @param pageIndex 页码
     * @param pageSize 页数
     */
    fun list(pageIndex: Long, pageSize: Long): ListResult<String>

    /**
     * 设置列表
     * @param list 列表
     */
    fun setItems(list: List<String>)

    /**
     * 添加项目
     * @param item 项目
     */
    fun addItem(item: String)

    /**
     * 删除项目
     * @param item 项目
     */
    fun removeItem(item: String)

}