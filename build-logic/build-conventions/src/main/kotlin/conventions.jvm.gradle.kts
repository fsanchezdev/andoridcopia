/*
 * Kotlin (JVM) library module (convention plugin).
 */

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
  id("org.jetbrains.kotlin.jvm")
  id("org.jmailen.kotlinter")
}

kotlin {
  explicitApi()
  jvmToolchain(libs.findVersion("java.sdk").get().toString().toInt())
  compilerOptions {
    allWarningsAsErrors.set(true)
  }
}

tasks.withType<org.jmailen.gradle.kotlinter.tasks.LintTask> {
  exclude { it.file.absolutePath.contains("/build/") }
}

dependencies {
  implementation(libs.findLibrary("kotlinx.coroutines").get())
  testImplementation(libs.findLibrary("kotlin.test").get())
  testImplementation(libs.findLibrary("kotlin.test.junit").get())
  testImplementation(libs.findLibrary("kotlinx.coroutinesTest").get())
  testImplementation(libs.findLibrary("junit").get())
}
