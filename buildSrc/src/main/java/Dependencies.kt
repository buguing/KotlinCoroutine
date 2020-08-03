object Versions {
    const val appId = "com.wellee.coroutine"
    const val minSDK = 26
    const val targetSDK = 29
    const val versionCode = 1
    const val versionName = "1.1.0"

    const val kotlin = "1.3.71"
    const val gradle = "3.6.4"
    const val appcompat = "1.1.0"
    const val constraintLayout = "1.1.3"

    const val ktxCoroutines = "1.3.7"
    const val coreKtx = "1.3.0"

    const val retrofit = "2.9.0"
    const val okhttp = "4.5.0"
    const val retrofitConverter = "2.8.1"

    const val rxjava = "3.0.4"
    const val rxandroid = "3.0.0"

    const val activityKtx = "1.1.0"
    const val lifecycleKtx = "2.2.0"
}

object Deps {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val ktxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.ktxCoroutines}"
    const val ktxCoroutinesAndorid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.ktxCoroutines}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverter}"
    const val adapter = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"

    const val rxjava = "io.reactivex.rxjava3:rxjava:${Versions.rxjava}"
    const val rxandroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxandroid}"

    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"

    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
    const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleKtx}"
    const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}"
}