package com.prueba.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface AbilitiesDao {
    @Insert(onConflict = OnConflictStrategy.NONE)
    fun insertAbility(vararg ability: AbilitiesObj?)

    @Query("SELECT * FROM AbilitiesObj WHERE id = :id AND name = :name")
    fun getAbilityByIdAndName(id: Int, name: String): Single<List<AbilitiesObj>>

    @Query("SELECT * FROM AbilitiesObj WHERE id = :id")
    fun getAbilityById(id: Int): Single<List<AbilitiesObj>>
}