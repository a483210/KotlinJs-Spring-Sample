package com.xy.kotlin.sample.server.filter

import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

/**
 * 静态资源路由
 *
 * @author Created by gold on 2020/10/15 11:45
 */
@Component
class StaticWebFilter : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        return if (exchange.request.uri.path == "/dashboard") {
            chain.filter(exchange.mutate()
                    .request(exchange.request
                            .mutate()
                            .path("/index.html")
                            .build())
                    .build())
        } else {
            chain.filter(exchange)
        }
    }

}