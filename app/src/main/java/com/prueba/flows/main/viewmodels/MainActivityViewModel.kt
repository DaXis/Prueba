package com.prueba.flows.main.viewmodels

import androidx.lifecycle.LiveData
import com.prueba.R
import com.prueba.common.base.BaseSingleLiveEvent
import com.prueba.common.base.BaseViewModel
import com.prueba.flows.main.actions.MainActions
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() : BaseViewModel() {

    private val action = BaseSingleLiveEvent<MainActions>()
    fun getAction(): LiveData<MainActions> = action

    fun getTypeMenu(type: Int) = if (type == 0) {
        R.menu.excursionist_menu
    } else {
        R.menu.guide_menu
    }

}