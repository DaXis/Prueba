package com.prueba.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prueba.db.ConstantsValues.DB_NAME
import com.prueba.db.ConstantsValues.DB_VERSION

@Database(entities = [PokemonObj::class, AbilitiesObj::class], version = DB_VERSION, exportSchema = false)
abstract class PokedexDataBase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun abilitiesDao(): AbilitiesDao

    companion object {

        @Volatile
        private var INSTANCE: PokedexDataBase? = null

        fun getInstance(context: Context): PokedexDataBase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDataBase(context).also { INSTANCE = it }
            }
        }

        private fun buildDataBase(context: Context): PokedexDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                PokedexDataBase::class.java,
                DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}