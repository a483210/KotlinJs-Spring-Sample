package com.xy.kotlin.sample.server.configure

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.config.WebFluxConfigurer

/**
 * jackson配置
 *
 * @author Created by gold on 2020/10/19 15:46
 */
@Configuration
class JacksonConfiguration {

    @Bean
    fun objectMapper(): ObjectMapper {
        return JsonMapper.builder()
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .addModule(KotlinModule())
                .build()
    }

    @Bean
    fun jackson2JsonEncoder(mapper: ObjectMapper): Jackson2JsonEncoder {
        return Jackson2JsonEncoder(mapper)
    }

    @Bean
    fun jackson2JsonDecoder(mapper: ObjectMapper): Jackson2JsonDecoder {
        return Jackson2JsonDecoder(mapper)
    }

    @Bean
    fun webFluxConfigurer(encoder: Jackson2JsonEncoder, decoder: Jackson2JsonDecoder): WebFluxConfigurer {
        return object : WebFluxConfigurer {
            override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
                configurer.defaultCodecs().jackson2JsonEncoder(encoder)
                configurer.defaultCodecs().jackson2JsonDecoder(decoder)
            }
        }
    }

}