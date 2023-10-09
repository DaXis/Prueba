package com.prueba.di.modules

import com.prueba.flows.main.api.PruebaServicesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApisModule {

    @Provides
    @Singleton
    fun provideBaseServicesApi(retrofit: Retrofit) = retrofit.create(PruebaServicesApi::class.java)
}