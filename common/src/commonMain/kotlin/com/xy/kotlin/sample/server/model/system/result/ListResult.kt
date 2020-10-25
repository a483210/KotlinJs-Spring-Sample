package com.xy.kotlin.sample.server.model.system.result

import kotlinx.serialization.Serializable

/**
 * 列表数据返回
 *
 * @author Created by gold on 2020/10/14 16:49
 */
@Serializable
data class ListResult<T>(
        /**
         * 数据集合
         */
        val list: List<T>,
        /**
         * 当前页码
         */
        val pageNumber: Int,
        /**
         * 当前页面数量大小
         */
        val pageSize: Int,
        /**
         * 总size
         */
        val total: Int,
        /**
         * 总页数
         */
        val totalPage: Int,
        /**
         * 是否有更多数据
         */
        val hasMore: Boolean,
) {

    constructor(result: ListResult<T>) :
            this(result.list,
                    result.pageNumber,
                    result.pageSize,
                    result.total,
                    result.totalPage,
                    result.hasMore)

    companion object {

        /**
         * 成功获取分页
         *
         * @param result 数据
         */
        fun <T> success(result: List<T>): ListResult<T> {
            return ListResult(result,
                    result.size,
                    1,
                    result.size,
                    1,
                    false
            )
        }

        /**
         * 空数据
         */
        fun <T> empty(): ListResult<T> {
            return ListResult(emptyList(),
                    1,
                    0,
                    0,
                    0,
                    false)
        }
    }

    /**
     * 是否为空
     */
    fun isEmpty(): Boolean {
        return list.isEmpty()
    }

}