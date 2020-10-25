package com.xy.kotlin.sample.server.controller

import com.xy.kotlin.sample.server.model.system.result.ServerResult
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

/**
 * HomeController
 *
 * @author Created by gold on 2020/10/19 14:20
 */
@RestController
@RequestMapping("/home")
class HomeController() {

    @GetMapping
    fun welcome(): Mono<ServerResult<String>> {
        return Mono.just(ServerResult.success("Welcome"))
    }

}