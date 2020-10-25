package com.xy.kotlin.sample.server.model.system.result

import com.xy.kotlin.sample.server.model.system.IServerError
import com.xy.kotlin.sample.server.type.ServerCode
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable

/**
 * ServerResult
 *
 * @author Created by gold on 2020/10/14 11:48
 */
@Serializable
data class ServerResult<T>(
        /**
         * 返回值
         */
        val result: T? = null,

        /**
         * 返回码
         * @see ServerCode
         */
        override val code: Int,
        /**
         * 错误信息
         */
        override val errorMessage: String? = null,

        /**
         * 时间戳
         */
        val timestamp: Long,
) : IServerError {

    constructor(result: ServerResult<T>) :
            this(result.result, result.code, result.errorMessage, result.timestamp)

    companion object {

        /**
         * 成功的返回
         *
         * @param result 返回值
         */
        fun <T> success(result: T): ServerResult<T> {
            return ServerResult(result, ServerCode.SUCCESS.code, null,
                    Clock.System.now().toEpochMilliseconds())
        }

        /**
         * 成功的列表返回
         *
         * @param result 返回值
         */
        fun <T> success(result: List<T>): ServerResult<ListResult<T>> {
            return ServerResult(ListResult.success(result), ServerCode.SUCCESS.code, null,
                    Clock.System.now().toEpochMilliseconds())
        }

        /**
         * 成功空返回
         */
        fun success(): ServerResult<NullResult> {
            return success(NullResult())
        }

        /**
         * 错误返回
         *
         * @param errorCode 错误码
         * @param errorMessage  错误信息
         */
        fun <T> error(errorCode: Int, errorMessage: String): ServerResult<T> {
            return ServerResult(null, errorCode, errorMessage,
                    Clock.System.now().toEpochMilliseconds())
        }

        /**
         * 错误返回
         *
         * @param error 错误枚举
         */
        fun <T> error(error: IServerError): ServerResult<T> {
            return ServerResult(null, error.code, error.errorMessage,
                    Clock.System.now().toEpochMilliseconds())
        }

        /**
         * 错误返回
         *
         * @param error    错误枚举
         * @param errorMessage 错误信息
         */
        fun <T> error(error: IServerError, errorMessage: String): ServerResult<T> {
            return ServerResult(null, error.code, errorMessage,
                    Clock.System.now().toEpochMilliseconds())
        }

    }

    /**
     * 是否成功
     */
    fun isSuccess(): Boolean {
        return ServerCode.SUCCESS.compareTo(code)
    }

}