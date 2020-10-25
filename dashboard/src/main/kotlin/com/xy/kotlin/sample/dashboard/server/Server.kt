package com.xy.kotlin.sample.dashboard.server

import com.xy.kotlin.sample.server.error.ServerException
import com.xy.kotlin.sample.server.model.system.result.ListResult
import com.xy.kotlin.sample.server.model.system.result.ServerResult
import com.xy.kotlin.sample.server.type.SystemError
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.browser.window
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

/**
 * Server
 *
 * @author Created by gold on 2020/10/17 16:41
 */

/**
 * 请求地址
 */
val endpoint = "http://localhost:9511" //window.location.origin

/**
 * json解析
 */
val json = Json {
    ignoreUnknownKeys = true
}

/**
 * 客户端
 */
val client = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }
}

/**
 * 发起get请求
 */
suspend fun <T> get(path: String,
                    serializer: KSerializer<T>,
                    block: HttpRequestBuilder.() -> Unit = {}): T {
    val response = client.get<String>(endpoint + path, block)

    return parse(serializer, response)
}

/**
 * 发起get列表请求
 */
suspend fun <T> getList(path: String,
                        serializer: KSerializer<T>,
                        block: HttpRequestBuilder.() -> Unit = {}): ListResult<T> {
    return get(path, ListResult.serializer(serializer), block)
}

/**
 * 发起post请求
 */
suspend fun <T> post(path: String,
                     serializer: KSerializer<T>,
                     block: HttpRequestBuilder.() -> Unit = {}): T {
    val response = client.post<String>(endpoint + path) {
        headers.append("content-type", "application/json")

        block()
    }

    return parse(serializer, response)
}

/**
 * 发起put请求
 */
suspend fun <T> put(path: String,
                    serializer: KSerializer<T>,
                    block: HttpRequestBuilder.() -> Unit = {}): T {
    val response = client.put<String>(endpoint + path, block)

    return parse(serializer, response)
}

/**
 * 发起delete请求
 */
suspend fun <T> delete(path: String,
                       serializer: KSerializer<T>,
                       block: HttpRequestBuilder.() -> Unit = {}): T {
    val response = client.delete<String>(endpoint + path, block)

    return parse(serializer, response)
}

private fun <T> parse(serializer: KSerializer<T>, response: String): T {
    val result: ServerResult<T> = json.decodeFromString(ServerResult.serializer(serializer), response)
    if (!result.isSuccess()) {
        throw ServerException(result)
    }

    return result.result ?: throw ServerException(SystemError.ERROR_NULL)
}