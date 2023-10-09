package com.prueba.di.component

import com.prueba.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        PrudentialValidationAppModule::class,
        ActivityModule::class,
        ApisModule::class,
        FragmentModule::class,
        RetrofitModule::class,
        DataBaseModule::class
    ]
)

interface MainComponent : BaseComponent {
    @Component.Builder
    interface Builder {
        fun build(): MainComponent

        @BindsInstance
        fun application(application: InjectableApplication): Builder
    }
}