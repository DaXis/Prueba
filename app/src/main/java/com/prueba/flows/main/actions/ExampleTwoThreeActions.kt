package com.prueba.flows.main.actions

import com.prueba.db.PokemonObj

sealed class ExampleTwoThreeActions {
    data class GetAllPokemon(val list: List<PokemonObj>) : ExampleTwoThreeActions()
    data class GetPokemon(val poke: PokemonObj) : ExampleTwoThreeActions()
}