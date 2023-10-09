package com.prueba.flows.main.viewmodels

import androidx.lifecycle.LiveData
import com.prueba.common.base.BaseSingleLiveEvent
import com.prueba.common.base.BaseViewModel
import com.prueba.flows.main.actions.ExampleTwoTrheeActions
import com.prueba.flows.main.api.repository.PruebaRepository
import com.prueba.utils.PruebaPreferencesManager
import javax.inject.Inject

class ExampleTwoThreeViewModel @Inject constructor(
    private val repository: PruebaRepository,
    private val provider: PruebaPreferencesManager
) : BaseViewModel() {

    private val action = BaseSingleLiveEvent<ExampleTwoTrheeActions>()
    fun getAction(): LiveData<ExampleTwoTrheeActions> = action

    private companion object {

    }
}