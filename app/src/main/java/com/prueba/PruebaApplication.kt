package com.prueba

import com.prueba.common.base.BaseApplication
import com.prueba.di.component.DaggerMainComponent

class PruebaApplication : BaseApplication() {

    companion object {
        lateinit var instance: PruebaApplication
            private set
    }

    override fun initializeInjector() {
        component = DaggerMainComponent.builder()
            .application(this)
            .build()
            .apply { inject(this@PruebaApplication) }
    }

    override fun onCreate() {
        super.onCreate()
    }
}