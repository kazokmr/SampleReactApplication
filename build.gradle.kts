plugins {
    kotlin("js") version "1.5.31"
}

group = "me.kazokmr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.262-kotlin-1.5.31")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.262-kotlin-1.5.31")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.3-pre.262-kotlin-1.5.31")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")
    implementation(npm("react", "17.0.2"))
    implementation(npm("react-dom", "17.0.2"))
    implementation(npm("styled-components", "^5.3.3"))
    implementation(npm("react-youtube-lite", "^1.1.0"))
    implementation(npm("react-share", "^4.4.0"))

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.31")
}

kotlin {
    js(LEGACY) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
}

// This block is from https://github.com/JetBrains/compose-jb/issues/1259
afterEvaluate {
    rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
        versions.webpackCli.version = "4.9.0"
    }
}