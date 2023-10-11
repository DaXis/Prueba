package com.prueba.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface AbilitiesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAbility(vararg ability: AbilitiesObj?)
}