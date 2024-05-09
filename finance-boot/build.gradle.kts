
plugins {
    id("org.springframework.boot") version "3.2.5"
}

apply(plugin = "org.springframework.boot")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA 支持
    implementation("org.springframework.boot:spring-boot-starter-jdbc") // JDBC 支持
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("com.mysql:mysql-connector-j:8.3.0")
    implementation(project(":finance-common")) // 依赖 common 模块
    implementation(project(":finance-framework"))// 依赖framework 模块
}

tasks.withType<Test> {
    useJUnitPlatform()
}