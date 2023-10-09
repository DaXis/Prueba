package com.prueba.flows.main.viewmodels

import androidx.lifecycle.LiveData
import com.prueba.common.base.BaseSingleLiveEvent
import com.prueba.common.base.BaseViewModel
import com.prueba.flows.main.actions.ExampleTwoTrheeActions
import com.prueba.flows.main.api.repository.PruebaRepository
import javax.inject.Inject

class PokeDetailViewModel @Inject constructor(
    private val repository: PruebaRepository
) : BaseViewModel() {

    private val action = BaseSingleLiveEvent<ExampleTwoTrheeActions>()
    fun getAction(): LiveData<ExampleTwoTrheeActions> = action

    private companion object {

    }
}