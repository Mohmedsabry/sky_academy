plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.skyacademy.core_librarys"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
    api(libs.firebase.messaging)
    api(libs.firebase.crashlytics)
    api(libs.kotlinx.coroutines.test)
    api(libs.mockk)
    api(libs.work.runtime.ktx)

    //Retrofit
    api(libs.retrofit)
    api(libs.logging.interceptor)
    api(libs.converter.gson)

    // Room
    api(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    api(libs.androidx.room.ktx)
    // coil
    api(libs.coil.compose)

    // ViewModel
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.sdp.compose)
    // exposed DropDown
    api(libs.material3)
    api(libs.accompanist.permissions)
    api(libs.play.services.location)
    // kotlin serialization
    api(libs.kotlin.serialization.json)
    // biometric auth
    api(libs.biometric)
    // Encrypt SharedPreferences
    api(libs.androidx.security.crypto)
    // SQLCipher for encryption
    implementation(libs.sqlcipher.android)
    // Icons
    api(libs.androidx.material.icons.core)
    api(libs.androidx.material.icons.extended)
    debugApi(libs.library)
    releaseApi(libs.chucker.no.op)
    // koin
    api(libs.koin.core)
    api(libs.koin.android)
}