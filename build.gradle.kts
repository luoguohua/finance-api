plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

val versions = mapOf(
		"lombok" to "1.18.26",
		"springBoot" to "3.2.5",
)

allprojects {
	group = "com.luoguohua.finance"
	version = "0.0.1-SNAPSHOT"

	// 仓库配置
	repositories {
		maven {
			setUrl("https://maven.aliyun.com/repository/public")
		}
		mavenCentral()
	}

}

// 定义子项目共享依赖
subprojects {
	apply(plugin = "java")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")

	java {
		sourceCompatibility = JavaVersion.VERSION_21
		targetCompatibility = JavaVersion.VERSION_21
	}

	dependencies {
		implementation("org.projectlombok:lombok:${versions["lombok"]}")
		annotationProcessor("org.projectlombok:lombok:${versions["lombok"]}")
		implementation("org.springframework.boot:spring-boot-starter-web")
	}
}

