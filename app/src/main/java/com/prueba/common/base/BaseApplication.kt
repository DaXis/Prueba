package com.prueba.common.base

import android.content.Context
import androidx.multidex.MultiDex
import com.prueba.di.component.BaseComponent
import com.prueba.di.component.InjectableApplication

abstract class BaseApplication : InjectableApplication() {

    var component: BaseComponent? = null
        protected set

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        var instance: BaseApplication? = null
            private set
    }
}