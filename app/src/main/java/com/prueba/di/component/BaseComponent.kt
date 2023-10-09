package com.prueba.di.component

import android.app.Application
import android.content.Context
import dagger.android.AndroidInjector

interface BaseComponent : AndroidInjector<InjectableApplication> {
    fun context(): Context
    fun application(): Application
}