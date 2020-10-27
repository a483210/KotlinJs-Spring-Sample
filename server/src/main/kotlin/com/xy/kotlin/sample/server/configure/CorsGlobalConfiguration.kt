package com.xy.kotlin.sample.server.configure

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

/**
 * 跨域配置
 *
 * @author Created by gold on 2020/10/17 17:58
 */
@Configuration
class CorsGlobalConfiguration : WebFluxConfigurer {

    override fun addCorsMappings(corsRegistry: CorsRegistry) {
        corsRegistry.addMapping("/home/**")
                .allowedOrigins("*")
                .allowedMethods("*")
    }

}