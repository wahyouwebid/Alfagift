import buildtype.AndroidBuildType.DEBUG
import buildtype.AndroidBuildType.RELEASE
import config.AndroidConfig
import dependencies.Dependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        namespace = AndroidConfig.appNameSpace
        minSdk = AndroidConfig.minSdk
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
        testInstrumentationRunner = AndroidConfig.testInstrumentRunner
    }

    buildTypes {
        named(DEBUG) {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "baseUrl", "\"https://newsapi.org/v2/\"")
            buildConfigField("String", "apiKey", "\"3b47ced24a7c4e7798e1b4d69f3ad4a4\"")
        }

        named(RELEASE) {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "baseUrl", "\"https://newsapi.org/v2/\"")
            buildConfigField("String", "apiKey", "\"f3aa09e2f1d7494e9fded68f30d4e4c1\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    Dependencies.AndroidLib.apply {
        implementation(core)
        implementation(appCompat)
        implementation(navigationIu)
        implementation(navigationFragment)
        implementation(constraintLayout)
        implementation(pagingRuntime)
        implementation(pagingRxJava)
    }

    Dependencies.UiLib.apply {
        implementation(material)
        implementation(glide)
        kapt(glideCompiler)
        implementation(pretty)
    }

    Dependencies.NetworkingLib.apply {
        implementation(retrofit2)
        implementation(rxJava)
        implementation(converterGson)
        implementation(okhttp)
        implementation(okhttpInterceptor)
        debugImplementation(chucker)
        releaseImplementation(chuckerNoop)
    }

    Dependencies.DependencyInjectionLib.apply {
        implementation(hilt)
        kapt(hiltCompiler)
        kapt(hiltAndroidCompiler)
    }

    Dependencies.ReactiveLib.apply {
        implementation(rxAndroid)
        implementation(rxJava)
        implementation(rxBinding)
    }

    Dependencies.TestingLib.apply {
        testImplementation(androidJunit)
        androidTestImplementation(espresso)
        androidTestImplementation(junit)
    }
}
