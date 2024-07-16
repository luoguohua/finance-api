plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

val versions = mapOf(
		"lombok" to "1.18.30",
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
		implementation("cn.hutool:hutool-all:5.8.25")
		implementation("org.mapstruct:mapstruct:1.5.5.Final")
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA 支持
		implementation("org.springframework.boot:spring-boot-starter-jdbc") // JDBC 支持
		implementation("org.springframework.boot:spring-boot-starter-security")
		implementation("org.springframework.boot:spring-boot-starter-validation") // validation 支持
		implementation("org.springframework.boot:spring-boot-starter-data-redis") // redis 支持
		implementation("org.postgresql:postgresql:42.4.4") // postgresql 支持
		implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0") // springdoc
	}
}

tasks.withType(JavaCompile::class.java){
	options.encoding="UTF-8"
}