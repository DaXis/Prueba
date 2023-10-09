package com.prueba.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single

@Dao
interface OCRDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOCRData(vararg data: OCRDataObj?)

    @Query("SELECT * FROM OCRDataObj WHERE id = :id")
    fun getUserDataById(id: String): Single<List<OCRDataObj>>

    @Update()
    fun updateUserData(ocr: OCRDataObj): Single<Int>
}