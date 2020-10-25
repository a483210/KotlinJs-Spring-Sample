package com.xy.kotlin.sample.server.type

import com.xy.kotlin.sample.server.model.system.IServerError

/**
 * ServerCode
 *
 * @author Created by gold on 2020/10/14 11:50
 */
enum class ServerCode(override val code: Int,
                      override val errorMessage: String?
) : IServerError {

    /**
     * 成功
     */
    SUCCESS(200, "success");

    /**
     * 是否相等
     */
    fun compareTo(code: Int): Boolean {
        return code == this.code
    }

}