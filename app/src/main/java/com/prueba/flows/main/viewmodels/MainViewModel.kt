package com.prueba.flows.main.viewmodels

import androidx.lifecycle.LiveData
import com.prueba.common.base.BaseSingleLiveEvent
import com.prueba.common.base.BaseViewModel
import com.prueba.flows.main.actions.MainActions
import com.prueba.flows.main.api.repository.PruebaRepository
import com.prueba.utils.PruebaPreferencesManager
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: PruebaRepository,
    private val provider: PruebaPreferencesManager
) : BaseViewModel() {

    private val action = BaseSingleLiveEvent<MainActions>()
    fun getAction(): LiveData<MainActions> = action


    private companion object {
    }
}