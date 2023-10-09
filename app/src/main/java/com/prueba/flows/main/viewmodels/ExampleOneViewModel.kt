package com.prueba.flows.main.viewmodels

import androidx.lifecycle.LiveData
import com.prueba.common.base.BaseSingleLiveEvent
import com.prueba.common.base.BaseViewModel
import com.prueba.flows.main.actions.ExampleOneActions
import com.prueba.flows.main.api.repository.PruebaRepository
import javax.inject.Inject

class ExampleOneViewModel @Inject constructor(
    private val repository: PruebaRepository
) : BaseViewModel() {

    private val action = BaseSingleLiveEvent<ExampleOneActions>()
    fun getAction(): LiveData<ExampleOneActions> = action

    private companion object {
    }
}