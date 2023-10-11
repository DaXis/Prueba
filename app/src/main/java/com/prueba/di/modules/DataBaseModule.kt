package com.prueba.di.modules

import android.content.Context
import com.prueba.db.PokedexDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context) = PokedexDataBase.getInstance(context)

    @Provides
    @Singleton
    fun providePokemonDao(db: PokedexDataBase) = db.pokemonDao()

    @Provides
    @Singleton
    fun provideAbilitiesDao(db: PokedexDataBase) = db.abilitiesDao()
}