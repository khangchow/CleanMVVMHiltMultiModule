package com.chow.cleanmvvmhiltmultimodule.presentation

import android.app.Application
import com.chow.cleanmvvmhiltmultimodule.BuildConfig
import com.chow.cleanmvvmhiltmultimodule.utils.DebuggingActivityLifecycleCallback
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            registerActivityLifecycleCallbacks(DebuggingActivityLifecycleCallback())
        }
    }
}