import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.71"
    kotlin("plugin.spring") version "1.3.71"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

configurations {
    implementation {
        resolutionStrategy.failOnVersionConflict()
    }
}

dependencies {
    listOf(
            "spring-boot-starter-webflux",
            "spring-boot-starter-${project.ext["server.type"]}",
            "spring-boot-starter"
    ).forEach { name ->
        implementation("org.springframework.boot:${name}") {
            exclude(module = "spring-boot-starter-logging")
        }
    }
    implementation("io.netty:netty-tcnative-boringssl-static:2.0.29.Final")

    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.projectreactor.addons:reactor-adapter:3.3.2.RELEASE")
    implementation("org.yaml:snakeyaml:1.26")
    implementation("io.github.skhatri:mounted-secrets-client:0.2.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.3.9")
    implementation("org.apache.kafka:kafka-streams:2.6.0")
    implementation("org.apache.kafka:kafka_2.12:2.6.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
        exclude(module = "spring-boot-starter-logging")
    }
    testImplementation("io.projectreactor:reactor-test:3.3.2.RELEASE")
    testImplementation("org.mockito:mockito-core:3.3.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.6.1")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.6.1")

    testImplementation("org.junit.platform:junit-platform-commons:1.6.1")
    testImplementation("org.junit.platform:junit-platform-runner:1.6.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.6.1")
    testRuntimeOnly("org.junit.platform:junit-platform-engine:1.6.1")
    testImplementation("org.testcontainers:testcontainers:1.13.0")
    testImplementation("org.testcontainers:junit-jupiter:1.13.0")
    testImplementation("org.testcontainers:postgresql:1.13.0")

}


task("runApp", JavaExec::class) {
    main = "com.github.starter.ApplicationKt"
    classpath = sourceSets["main"].runtimeClasspath
    jvmArgs = listOf(
            "-Xms512m", "-Xmx512m"
    )
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
