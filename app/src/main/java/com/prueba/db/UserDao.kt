package com.prueba.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user: UserObj?)

    @Query("SELECT * FROM UserObj WHERE id = :id")
    fun getUserById(id: String): Single<List<UserObj>>
}