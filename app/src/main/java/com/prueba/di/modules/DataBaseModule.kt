package com.prueba.di.modules

import android.content.Context
import com.prueba.db.PrudentialDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context) = PrudentialDataBase.getInstance(context)

    @Provides
    @Singleton
    fun provideUserDao(db: PrudentialDataBase) = db.userDao()

    @Provides
    @Singleton
    fun provideOCRDataDao(db: PrudentialDataBase) = db.ocrDataDao()
}