package com.prueba.flows.main.viewmodels

import androidx.lifecycle.LiveData
import com.prueba.common.base.BaseSingleLiveEvent
import com.prueba.common.base.BaseViewModel
import com.prueba.db.PokemonObj
import com.prueba.db.PokemonRepository
import com.prueba.flows.main.actions.ExampleTwoThreeActions
import com.prueba.flows.main.api.models.response.PokedexResult
import com.prueba.flows.main.api.repository.PruebaRepository
import com.prueba.utils.UtilsExtensions.orZero
import com.prueba.utils.UtilsExtensions.toPokeId
import com.prueba.utils.UtilsExtensions.toSprite
import javax.inject.Inject

class ExampleTwoThreeViewModel @Inject constructor(
    private val repository: PruebaRepository,
    private val dbRepository: PokemonRepository
) : BaseViewModel() {

    private val action = BaseSingleLiveEvent<ExampleTwoThreeActions>()
    fun getAction(): LiveData<ExampleTwoThreeActions> = action

    private var count = 1

    fun consumePokeList(offset: String) {
        disposable.add(
            repository.consumePokeList(offset)
                .doOnSubscribe { showProgress.value = true }
                .subscribe(
                    {
                        it?.let {
                            prepareList(it.results)
                        } ?: run {

                        }
                    },
                    {
                        showErrorMessage.value = getServiceError(it)
                        showProgress.value = false
                    }
                )
        )
    }

    private fun prepareList(results: List<PokedexResult>?) {
        results?.let { pokedex ->
            val list = ArrayList<PokemonObj>()
            pokedex.forEach { pokemon ->
                val pokeId = pokemon.url?.toPokeId().orZero()
                list.add(
                    PokemonObj(
                        id = pokeId,
                        name = pokemon.name,
                        sprite = pokeId.toSprite()
                    )
                )
            }
            list.forEach {
                insertPokedex(it, list.size)
            }
        }
    }

    private fun insertPokedex(pokemon: PokemonObj, size: Int) {
        disposable.add(
            dbRepository.insertPokemon(
                pokemon
            )
                .subscribe(
                    {
                        if (count == size) {
                            getAllPokemon()
                            count = 1
                        } else {
                            count++
                        }
                    },
                    {
                        showErrorMessage.value = "error"
                    }
                )
        )
    }

    private fun getAllPokemon() {
        disposable.add(
            dbRepository.getAllPokemon()
                .doFinally { showProgress.value = false }
                .subscribe(
                    {
                        action.value = ExampleTwoThreeActions.GetAllPokemon(it)
                    },
                    {
                        showErrorMessage.value = "error"
                    }
                )
        )
    }
}