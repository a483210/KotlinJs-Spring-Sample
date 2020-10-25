package com.xy.kotlin.sample.server.type

import com.xy.kotlin.sample.server.model.system.IServerError

/**
 * SystemError
 *
 * @author Created by gold on 2020/10/19 14:33
 */
enum class SystemError(override val code: Int,
                       override val errorMessage: String?
) : IServerError {

    /**
     * 异常
     */
    ERROR(500, "error"),

    /**
     * 返回值为空
     */
    ERROR_NULL(501, "返回值为空"),
    ;

    /**
     * 是否相等
     */
    fun compareTo(code: Int): Boolean {
        return code == this.code
    }

}