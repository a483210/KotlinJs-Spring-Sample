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

    /**
     * 非法参数
     */
    ERROR_ILLEGAL_PARAMS(502, "非法参数"),

    /**
     * 资源已存在
     */
    ERROR_EXIST(10001, "资源已存在"),

    /**
     * 资源不存在
     */
    ERROR_NOT_EXIST(10002, "资源不存在"),
    ;

    /**
     * 是否相等
     */
    fun compareTo(code: Int): Boolean {
        return code == this.code
    }

}