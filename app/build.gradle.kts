plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.demo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.demo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        dataBinding=true
        viewBinding=true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("org.kodein.di:kodein-di-generic-jvm:6.3.3")
    implementation ("org.kodein.di:kodein-di-framework-android-x:6.3.3")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")


    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation ("com.intuit.sdp:sdp-android:1.1.1")
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("android.arch.lifecycle:livedata:1.1.1")



    val kotlinVersion = "2.8.5"
    // Java language implementation
    implementation ("androidx.navigation:navigation-fragment-ktx:$kotlinVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$kotlinVersion")
    // Kotlin
    implementation ("androidx.navigation:navigation-fragment-ktx:$kotlinVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$kotlinVersion")
    // Feature module Support
    implementation ("androidx.navigation:navigation-dynamic-features-fragment:$kotlinVersion")
    // Testing Navigation
    androidTestImplementation ("androidx.navigation:navigation-testing:$kotlinVersion")
    // Jetpack Compose Integration
    implementation ("androidx.navigation:navigation-compose:$kotlinVersion")

}