plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)


    alias(libs.plugins.google.gms.google.services)
}

android {
    buildFeatures {
        viewBinding = true
    }

    namespace = "com.example.real_timedeliverytrackingapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.real_timedeliverytrackingapp"
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
}

dependencies {
    // AndroidX dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)


    // Navigation dependencies
    val nav_version = "2.8.2"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // CircleImageView library
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Google Maps dependencies
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Firebase dependencies
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
}


