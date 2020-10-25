package com.xy.kotlin.sample.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File

/**
 * SampleServerApplication
 *
 * @author Created by gold on 2020/9/1 20:10
 */
@SpringBootApplication
class SampleServerApplication

fun main(args: Array<String>) {
    runApplication<SampleServerApplication>(*args)
}