plugins {
    val kotlinVersion = "1.6.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.12.1"
    id("com.github.johnrengelman.shadow") version "1.2.3"
}

dependencies {
    implementation("com.github.promeg:tinypinyin:2.0.3")
    implementation("com.github.promeg:tinypinyin-lexicons-java-cncity:2.0.3")
    implementation("org.json:json:20220320")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation("org.jsoup:jsoup:1.15.2")
}

version = "0.2.0-pre1"

repositories {
    maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    maven { url = uri("https://repo.repsy.io/mvn/chrynan/public") }
    mavenCentral()
}
