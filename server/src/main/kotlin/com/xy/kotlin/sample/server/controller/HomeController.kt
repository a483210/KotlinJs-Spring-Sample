package com.xy.kotlin.sample.server.controller

import com.xy.kotlin.sample.server.model.request.SetItemsRequest
import com.xy.kotlin.sample.server.model.system.request.ListRequest
import com.xy.kotlin.sample.server.model.system.result.ListResult
import com.xy.kotlin.sample.server.model.system.result.NullResult
import com.xy.kotlin.sample.server.model.system.result.ServerResult
import com.xy.kotlin.sample.server.service.HomeService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

/**
 * HomeController
 *
 * @author Created by gold on 2020/10/19 14:20
 */
@RestController
@RequestMapping("/home")
class HomeController(val homeService: HomeService) {

    @GetMapping
    fun welcome(): Mono<String> {
        return Mono.just("Welcome")
    }

    @GetMapping("/items")
    fun items(request: ListRequest): Mono<ServerResult<ListResult<String>>> {
        return Mono.just(ServerResult.success(homeService.list(request.pageIndex, request.pageSize)))
    }

    @PostMapping("/items")
    fun setItems(@RequestBody request: SetItemsRequest): Mono<ServerResult<NullResult>> {
        homeService.setItems(request.list)

        return Mono.just(ServerResult.success())
    }

    @PutMapping("/items")
    fun addItem(item: String): Mono<ServerResult<NullResult>> {
        homeService.addItem(item)

        return Mono.just(ServerResult.success())
    }

    @DeleteMapping("/items")
    fun removeItem(item: String): Mono<ServerResult<NullResult>> {
        homeService.removeItem(item)

        return Mono.just(ServerResult.success())
    }

}