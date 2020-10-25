package com.xy.kotlin.sample.server.configure

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.xy.kotlin.sample.server.error.ServerException
import com.xy.kotlin.sample.server.model.system.result.NullResult
import com.xy.kotlin.sample.server.model.system.result.ServerResult
import com.xy.kotlin.sample.server.type.SystemError
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

/**
 * 全局异常处理
 *
 * @author Created by gold on 2020/10/19 14:56
 */
@Configuration
@Order(-2)
class GlobalErrorWebExceptionHandler(
        val objectMapper: ObjectMapper
) : ErrorWebExceptionHandler {

    override fun handle(serverWebExchange: ServerWebExchange, throwable: Throwable): Mono<Void> {
        return when (throwable) {
            is ServerException -> handleServerException(serverWebExchange, throwable)
            else -> handleException(serverWebExchange, throwable)
        }
    }

    fun handleServerException(exchange: ServerWebExchange, t: ServerException): Mono<Void> {
        val result = ServerResult.error<NullResult>(t)

        return handleServerResult(exchange, result)
    }

    fun handleException(exchange: ServerWebExchange, t: Throwable): Mono<Void> {
        val message = t.message ?: return handleUnknownException(exchange, t)

        val result = ServerResult.error<NullResult>(SystemError.ERROR, message)

        return handleServerResult(exchange, result)
    }

    private fun handleServerResult(exchange: ServerWebExchange, result: ServerResult<NullResult>): Mono<Void> {
        val dataBuffer = try {
            val bufferFactory = exchange.response.bufferFactory()

            @Suppress("BlockingMethodInNonBlockingContext")
            bufferFactory.wrap(objectMapper.writeValueAsBytes(result))
        } catch (e: JsonProcessingException) {
            return handleUnknownException(exchange, e)
        }

        exchange.response.statusCode = HttpStatus.OK
        exchange.response.headers.contentType = MediaType.APPLICATION_JSON

        return exchange.response.writeWith(Mono.just(dataBuffer))
    }

    fun handleUnknownException(exchange: ServerWebExchange, t: Throwable): Mono<Void> {
        val bufferFactory = exchange.response.bufferFactory()

        val dataBuffer = bufferFactory.wrap((t.message ?: "Unknown error").toByteArray())

        exchange.response.statusCode = HttpStatus.INTERNAL_SERVER_ERROR
        exchange.response.headers.contentType = MediaType.TEXT_PLAIN

        return exchange.response.writeWith(Mono.just(dataBuffer))
    }

}