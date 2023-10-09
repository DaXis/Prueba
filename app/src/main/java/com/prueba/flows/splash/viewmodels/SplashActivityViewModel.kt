package com.prueba.flows.splash.viewmodels

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import com.prueba.common.base.BaseSingleLiveEvent
import com.prueba.common.base.BaseViewModel
import com.prueba.flows.splash.actions.SplashActions
import javax.inject.Inject

class SplashActivityViewModel @Inject constructor() : BaseViewModel() {

    private val action = BaseSingleLiveEvent<SplashActions>()
    fun getAction(): LiveData<SplashActions> = action

    fun goToMain() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                action.value = SplashActions.NavigateToMain
            },
            DURATION_SPLASH
        )
    }

    private companion object {
        const val DURATION_SPLASH = 2500L
    }

}