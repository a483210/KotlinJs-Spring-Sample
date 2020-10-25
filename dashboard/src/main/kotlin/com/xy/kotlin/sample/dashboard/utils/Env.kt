package com.xy.kotlin.sample.dashboard.utils

/**
 * Env
 *
 * @author Created by gold on 2020/10/21 14:02
 */
external val process: Process

external interface Process {

    val env: dynamic
}

/**
 * 获取环境变量，也可以通过以下方式获取
 * 1、process.env.xxx
 * 2、js("process.env.xxx")
 */
fun env(name: String): String? {
    return process.env[name] as? String ?: return null
}

/**
 * node环境，布署相关
 */
val NODE_ENV: NodeEnv = when (process.env.NODE_ENV) {
    NodeEnv.DEVELOPMENT.env -> NodeEnv.DEVELOPMENT
    NodeEnv.TEST.env -> NodeEnv.TEST
    NodeEnv.PRODUCTION.env -> NodeEnv.PRODUCTION
    else -> throw RuntimeException("unknownNodeEnv")
}

/**
 * @see [link](https://create-react-app.dev/docs/adding-custom-environment-variables/)
 */
enum class NodeEnv(val env: String) {

    /**
     * 开发模式
     */
    DEVELOPMENT("development"),

    /**
     * 测试模式
     */
    TEST("test"),

    /**
     * 正式环境
     */
    PRODUCTION("production")

}

/**
 * build环境，是否独立
 */
val BUILD_ENV: BuildEnv = when (process.env.js.buildEnv) {
    BuildEnv.RUN.env -> BuildEnv.RUN
    BuildEnv.WEBPACK.env -> BuildEnv.WEBPACK
    else -> throw RuntimeException("unknownBuildEnv")
}

enum class BuildEnv(val env: String) {

    /**
     * 独立运行模式
     */
    RUN("run"),

    /**
     * 包装模式
     */
    WEBPACK("webpack")

}

/**
 * 服务环境
 */
val ACTION_ENV: ActionEnv = when (process.env.js.actionEnv) {
    ActionEnv.LOCAL.env -> ActionEnv.LOCAL
    ActionEnv.DEV.env -> ActionEnv.DEV
    ActionEnv.TEST.env -> ActionEnv.TEST
    ActionEnv.PRE.env -> ActionEnv.PRE
    ActionEnv.PROD.env -> ActionEnv.PROD
    else -> throw RuntimeException("unknownActionEnv")
}

enum class ActionEnv(val env: String) {

    /**
     * 本地
     */
    LOCAL("local"),

    /**
     * 开发
     */
    DEV("dev"),

    /**
     * 测试
     */
    TEST("test"),

    /**
     * 预发
     */
    PRE("pre"),

    /**
     * 线上
     */
    PROD("prod")

}