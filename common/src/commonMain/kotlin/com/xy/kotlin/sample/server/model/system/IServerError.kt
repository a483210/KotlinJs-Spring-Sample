package com.xy.kotlin.sample.server.model.system

import com.xy.kotlin.sample.server.type.ServerCode

/**
 * 异常枚举
 *
 * @author Created by gold on 2020/10/14 16:43
 */
interface IServerError {

    /**
     * 返回码
     * @see ServerCode
     */
    val code: Int

    /**
     * 错误信息
     */
    val errorMessage: String?

}