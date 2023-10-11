package com.prueba.flows.main.viewmodels

import androidx.lifecycle.LiveData
import com.prueba.common.base.BaseSingleLiveEvent
import com.prueba.common.base.BaseViewModel
import com.prueba.db.AbilitiesObj
import com.prueba.db.AbilitiesRepository
import com.prueba.db.PokemonObj
import com.prueba.db.PokemonRepository
import com.prueba.flows.main.actions.ExampleTwoThreeActions
import com.prueba.flows.main.api.models.response.PokeDetailResponse
import com.prueba.flows.main.api.models.response.TypesObj
import com.prueba.flows.main.api.repository.PruebaRepository
import javax.inject.Inject

class PokeDetailViewModel @Inject constructor(
    private val repository: PruebaRepository,
    private val dbRepository: PokemonRepository,
    private val abilitiesRepository: AbilitiesRepository
) : BaseViewModel() {

    private val action = BaseSingleLiveEvent<ExampleTwoThreeActions>()
    fun getAction(): LiveData<ExampleTwoThreeActions> = action

    private var pokeID = 0

    fun consumePokeList(pokeId: Int) {
        pokeID = pokeId
        disposable.add(
            repository.consumePokeDetail(pokeId.toString())
                .doOnSubscribe { showProgress.value = true }
                .subscribe(
                    {
                        it?.let {
                            updatePokemon(it)
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

    private fun updatePokemon(pokeData: PokeDetailResponse) {
        disposable.add(
            dbRepository.updatePokemonData(
                PokemonObj(
                    id = pokeID,
                    "",
                    "",
                    pokeData.height.toString(),
                    pokeData.order.toString(),
                    pokeData.weight.toString(),
                    pokeData.types[0].type.mane,
                    validateSecondType(pokeData.types)
                )
            ).subscribe(
                {
                    getPokemonById(pokeID)
                    prepareList(pokeData)
                },
                {
                    showErrorMessage.value = "error"
                }
            )
        )
    }

    private fun prepareList(pokeData: PokeDetailResponse) {
        val abilities = ArrayList<AbilitiesObj>()
        pokeData.abilities.forEach { ability ->
            abilities.add(
                AbilitiesObj(
                    id = pokeID,
                    name = ability.ability.name,
                    isHidden = ability.isHidden
                )
            )
        }

        abilities.forEach {
            checkAbilitiesRegister(it)
        }
    }

    private fun checkAbilitiesRegister(ability: AbilitiesObj) {
        disposable.add(
            abilitiesRepository.getAbilityByIdAndName(
                ability.id,
                ability.name
            ).subscribe(
                {
                    it?.let {
                        if (it.isEmpty()) {
                            insertAbilities(ability)
                        }
                    } ?: run {

                    }
                },
                {
                    showErrorMessage.value = "error"
                }
            )
        )
    }

    private fun insertAbilities(ability: AbilitiesObj) {
        disposable.add(
            abilitiesRepository.insertAbility(
                ability
            ).doFinally { showProgress.value = false }
                .subscribe(
                    {

                    },
                    {
                        showErrorMessage.value = "error"
                    }
                )
        )
    }

    private fun getPokemonById(id: Int) {
        disposable.add(
            dbRepository.getPokemonById(
                id.toString()
            ).doFinally { showProgress.value = false }
                .subscribe(
                    {
                        action.value = ExampleTwoThreeActions.GetPokemon(it)
                    },
                    {
                        showErrorMessage.value = "error"
                    }
                )
        )
    }

    private fun validateSecondType(types: List<TypesObj>) = if (types.size > 1) {
        types[1].type.mane
    } else {
        ""
    }

    private companion object {

    }
}