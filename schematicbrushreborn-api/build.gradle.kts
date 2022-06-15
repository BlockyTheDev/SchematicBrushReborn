plugins {
    java
    id("de.eldoria.library-conventions")
}

dependencies {
    api("de.eldoria", "eldo-util", "1.13.5")
    api("de.eldoria", "messageblocker", "1.1.1")
    api("net.kyori", "adventure-platform-bukkit", "4.1.0")
    api("net.kyori", "adventure-text-minimessage", "4.10.1")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.8.2")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}

publishData {
    useEldoNexusRepos()
    publishComponent("java")
}

publishing {
    publications.create<MavenPublication>("maven") {
        publishData.configurePublication(this)
        pom {
            url.set("https://github.com/eldoriarpg/schematicbrushreborn")
            developers {
                developer {
                    name.set("Florian Fülling")
                    organization.set("EldoriaRPG")
                    organizationUrl.set("https://github.com/eldoriarpg")
                }
            }
            licenses {
                license {
                    name.set("GNU Affero General Public License v3.0")
                    url.set("https://github.com/eldoriarpg/schematicbrushreborn/blob/master/LICENSE.md")
                }
            }
        }
    }

    repositories {
        maven {
            authentication {
                credentials(PasswordCredentials::class) {
                    username = System.getenv("NEXUS_USERNAME")
                    password = System.getenv("NEXUS_PASSWORD")
                }
            }

            setUrl(publishData.getRepository())
            name = "EldoNexus"
        }
    }
}

tasks {
    test {
        dependsOn(licenseCheck)
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    withType<Javadoc> {
        val options = options as StandardJavadocDocletOptions
        options.links(
            "https://hub.spigotmc.org/javadocs/spigot/",
            "https://eldoriarpg.github.io/eldo-util/"
        )
    }
}