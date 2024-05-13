
plugins {
    id("org.springframework.boot") version "3.2.5"
}

apply(plugin = "org.springframework.boot")

dependencies {
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(project(":finance-framework"))// 依赖framework 模块
}

tasks.withType<Test> {
    useJUnitPlatform()
}