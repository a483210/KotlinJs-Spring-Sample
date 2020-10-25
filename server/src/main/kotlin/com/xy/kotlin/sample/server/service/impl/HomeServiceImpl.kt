package com.xy.kotlin.sample.server.service.impl

import com.xy.kotlin.sample.server.error.ServerException
import com.xy.kotlin.sample.server.model.system.result.ListResult
import com.xy.kotlin.sample.server.service.HomeService
import com.xy.kotlin.sample.server.type.SystemError
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import kotlin.streams.toList

/**
 * HomeServiceImpl
 *
 * @author Created by gold on 2020/10/19 14:28
 */
@Service
class HomeServiceImpl : HomeService {

    private val items: MutableList<String> = mutableListOf("选项1", "选项2", "选项3")

    override fun list(pageIndex: Long, pageSize: Long): ListResult<String> {
        return ListResult.success(items.stream()
                .skip((pageIndex - 1) * pageSize)
                .limit(pageSize)
                .toList())
    }

    override fun setItems(list: List<String>) {
        items.clear()
        items.addAll(list)
    }

    override fun addItem(item: String) {
        if (items.contains(item)) {
            throw ServerException(SystemError.ERROR_EXIST)
        }

        items.add(item)
    }

    override fun removeItem(item: String) {
        if (!items.contains(item)) {
            throw ServerException(SystemError.ERROR_NOT_EXIST)
        }

        items.remove(item)
    }

}