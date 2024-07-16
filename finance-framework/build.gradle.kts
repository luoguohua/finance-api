plugins {
    id("java")
}

group = "com.luoguohua.finance"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":finance-common")) // 依赖 common 模块
}
