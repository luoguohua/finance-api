apply(plugin = "org.springframework.boot")

dependencies {
    "implementation"("org.springframework.boot:spring-boot-starter-web") // 示例依赖
//    "implementation"(project(":common")) // 依赖 common 模块
}

tasks.withType<Test> {
    useJUnitPlatform()
}