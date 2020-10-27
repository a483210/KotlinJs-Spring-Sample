# Kotlin/Js-Spring Sample2-Dashboard

**Kotlin多平台演示** 

Kotlin/Js(前端) + Kotlin/SpringWebFlux(后端)

![首页](images/home.png)

### 1、初始化

##### 1、本地添加**local.properties**文件

> 文件内容
importJs=false #启动server时是否引入dashboard工程，默认true，分离开发时设置为false提高编译数据

##### 2、环境配置

- 开发环境：<kbd>Modules Setting</kbd> > <kbd>Project</kbd> > <kbd>Project SDK</kbd> > <kbd>设置为Java1.8</kbd>

- 编译环境：<kbd>Preferences</kbd> > <kbd>Build, Execution, Deployment</kbd> > <kbd>Build Tools</kbd> > <kbd>Gradle</kbd> > <kbd>Build and run using 和 Run tests using</kbd> > <kbd>设置为Gradle (Default)</kbd>

- Java 1.8
- Kotlin 1.4.10
- Gradle 6.6.1-All

其他看build.gradle

##### 3、启动

**importJs为True**

- 启动SampleServerApplication
- Dashboard：http://localhost:9511/dashboard
- 服务接口：http://localhost:9511/home/items

**如果Dashboard独立启动**

- 执行<kbd>kotlin browser</kbd> > <kbd>browserDevelopmentRun</kbd>
- http://localhost:8080

##### 4、测试

使用server/src/test/http/homeClient.http请求测试

### ∞、其他

> [热加载](https://kotlinlang.org/docs/tutorials/javascript/dev-server-continuous-compilation.html) Arguments添加--continuous

> [其他看Main分枝](https://github.com/a483210/KotlinJs-Spring-Sample)
