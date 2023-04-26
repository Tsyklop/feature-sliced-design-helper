import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.date

fun properties(key: String) = providers.gradleProperty(key)

plugins {
    id("java")
    id("idea")
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
    id("org.jetbrains.intellij") version "1.13.3"
    id("org.jetbrains.changelog") version "2.0.0"
}

group = properties("pluginGroup").get()
version = properties("pluginVersion").get()

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

kotlin {
    jvmToolchain(11)
}

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))
    plugins.set(listOf("JavaScript"))
    updateSinceUntilBuild.set(true)
}

changelog {
    path.set("${project.projectDir}/${properties("pluginDescriptionPath")}")
    header.set(provider { "[${project.version}] - ${date()}" })
    itemPrefix.set("-")
    unreleasedTerm.set("Unreleased")
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    patchPluginXml {

        untilBuild.set("")

        version.set(properties("pluginVersion"))
        sinceBuild.set(properties("pluginSinceBuild"))

        /*changeNotes.set(provider {
            changelog.renderItem(changelog.getAll().values.take(2).last(), Changelog.OutputType.HTML)
        })*/

    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getenv("PUBLISH_TOKEN"))
        // Use beta versions like 2020.3-beta-1
        channels.set(properties("pluginVersion").map {
            listOf(it
                    .split('-')
                    .getOrElse(1) { "default" }
                    .split('.')
                    .first()
            )
        })
    }

    runPluginVerifier {
        val versions = properties("pluginVerifierIdeVersions")
                .get()
                .split(",")
                .map(String::trim)
                .filter(String::isNotEmpty)
        // Kinda useless since the pluginVerifier will cry out
        // anyway, but may not setting a version will be implemented at some point.
        if (versions.isNotEmpty()) {
            ideVersions.set(versions)
        }
    }

}
