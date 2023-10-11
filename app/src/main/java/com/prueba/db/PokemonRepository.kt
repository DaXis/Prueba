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

    fun getPokemonById(id: String) = pokemonDao.getPokemonById(id)
        .map {
            it
        }.applySchedulers()

    fun getAllPokemon() = pokemonDao.getAllPokemon()
        .map {
            it
        }.applySchedulers()

    fun updatePokemonData(pokemon: PokemonObj): Completable = Completable.fromCallable {
        pokemonDao.updatePokemonData(
            pokemon.id,
            pokemon.height.orEmpty(),
            pokemon.order.orEmpty(),
            pokemon.weight.orEmpty(),
            pokemon.typeOne.orEmpty(),
            pokemon.typeTwo.orEmpty()
        )
    }.applySchedulers()

}