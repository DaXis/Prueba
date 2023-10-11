package com.prueba.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPokemon(vararg pokemon: PokemonObj?)

    @Query("SELECT * FROM PokemonObj WHERE id = :id")
    fun getPokemonById(id: String): Single<List<PokemonObj>>

    @Query("SELECT * FROM PokemonObj")
    fun getAllPokemon(): Single<List<PokemonObj>>
}