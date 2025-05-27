plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.pain.space"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pain.space"
        minSdk = 24
        targetSdk = 35
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
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    implementation(libs.androidx.constraintlayout)
    implementation("androidx.webkit:webkit:1.13.0")
    implementation("com.intuit.sdp:sdp-android:1.1.1")
    implementation("com.airbnb.android:lottie:6.6.4")
    implementation("com.github.AtifSayings:Animatoo:1.0.1")
    implementation(platform("com.google.firebase:firebase-bom:32.2.0"))
    //implementation("com.google.firebase:firebase-appcheck-ktx")
    implementation("com.github.ibrahimsn98:SmoothBottomBar:1.7.9")
    implementation(project(":mylibrary"))
    implementation("com.github.qamarelsafadi:CurvedBottomNavigation:0.1.3")
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    implementation("com.google.firebase:firebase-functions-ktx:20.2.0")
    implementation("com.google.android.material:material:1.11.0")
    androidTestImplementation(libs.androidx.espresso.core)

// Kotlin Coroutines core and Android
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

}