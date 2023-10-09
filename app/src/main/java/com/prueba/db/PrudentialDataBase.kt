package com.prueba.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prueba.db.ConstantsValues.DB_NAME
import com.prueba.db.ConstantsValues.DB_VERSION

@Database(entities = [UserObj::class, OCRDataObj::class], version = DB_VERSION, exportSchema = false)
abstract class PrudentialDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun ocrDataDao(): OCRDataDao

    companion object {

        @Volatile
        private var INSTANCE: PrudentialDataBase? = null

        fun getInstance(context: Context): PrudentialDataBase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDataBase(context).also { INSTANCE = it }
            }
        }

        private fun buildDataBase(context: Context): PrudentialDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                PrudentialDataBase::class.java,
                DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}