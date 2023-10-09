package com.prueba.flows.splash.actions

sealed class SplashActions {
    object NavigateToMain : SplashActions()
    object SendFinish : SplashActions()
    object IsTablet : SplashActions()
}