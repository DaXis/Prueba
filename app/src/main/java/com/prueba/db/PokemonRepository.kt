package com.prueba.db

import com.prueba.utils.PruebaPreferencesManager
import com.prueba.utils.applySchedulers
import io.reactivex.Completable
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val provider: PruebaPreferencesManager
) {

    fun insertPokemon(pokemon: PokemonObj): Completable = Completable.fromCallable {
        pokemonDao.insertPokemon(pokemon)
    }.applySchedulers()

    /*fun insertAllPokemon(pokedex: List<PokemonObj>?): Completable = Completable.fromCallable {
        pokemonDao.insertAllPokemon(pokedex)
    }.applySchedulers()*/

    fun getPokemonById(id: String) = pokemonDao.getPokemonById(id)
        .map {
            it
        }.applySchedulers()

    fun getAllPokemon() = pokemonDao.getAllPokemon()
        .map {
            it
        }.applySchedulers()
}