package com.prueba.di.modules

import android.app.Application
import android.content.Context
import com.prueba.di.component.InjectableApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object PrudentialValidationAppModule {

    @Provides
    @Singleton
    @JvmStatic
    fun providesContext(app: InjectableApplication): Context = app.applicationContext

    @Provides
    @Singleton
    @JvmStatic
    fun providesNaatApplication(app: InjectableApplication): Application = app
}