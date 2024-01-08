plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

//kotlin {
//    jvmToolchain {
//        languageVersion.set(JavaLanguageVersion.of(8))
//    }
//    jvmToolchain(8)
//}


dependencies {
    // kotlin-reflect
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    testImplementation("junit:junit:4.13.2")

    implementation("net.ifok.image:pngquant-png:1.0.0")
}