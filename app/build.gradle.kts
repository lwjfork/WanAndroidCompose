import com.google.protobuf.gradle.*


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.protobuf.gradle.plugin)
}
android {
    namespace = "com.wan.android.compose"
    compileSdk = Integer.parseInt(libs.versions.compileSdk.get())

    defaultConfig {
        applicationId = "com.wan.android.compose"
        minSdk = Integer.parseInt(libs.versions.minSdk.get())
        targetSdk = Integer.parseInt(libs.versions.targetSdk.get())
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
//    sourceSets.getByName("main") {
//        java.srcDirs("src/main/java")
//        resources.srcDirs("src/res")
//        aidl.srcDirs("src/main/aidl")
//        manifest.srcFile("src/main/AndroidManifest.xml")
//        res.srcDirs("src/main/res")
//        assets.srcDirs("src/main/assets")
//    }
//    sourceSets.getByName("androidTest") {
//        java.srcDirs("src/main/java","src/androidTest/java")
//        resources.srcDirs("src/main/res")
//        aidl.srcDirs("src/main/aidl")
//        manifest.srcFile("src/androidTest/AndroidManifest.xml")
//        res.srcDirs("src/androidTest/res")
//    }
}



dependencies {
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.navigation.fragment.ktx)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.kotlin.poet)
    debugImplementation(libs.kotlin.poet)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(platform(libs.okhttp3.bom))
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.log)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.coroutins)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.viewpager2)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.datastore)
    implementation("com.google.protobuf:protobuf-javalite:3.18.0")
}


protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.19.4"
    }
    plugins {
        id("java") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.47.0"
        }
//        id("grpc") {
//            artifact = "io.grpc:protoc-gen-grpc-java:1.47.0"
//        }
//        id("grpckt") {
//            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
//        }
    }
    generateProtoTasks{
        all().forEach {
            it.plugins {
//                id("java") {
//                    option("lite")
//                }
//                id("grpc") {
//                    option("lite")
//                }
//                id("grpckt") {
//                    option("lite")
//                }
            }
            it.builtins {
                id("java") {
                    option("lite")
                }
            }
        }
    }
}