package com.prueba.flows.main.actions

sealed class MainActions {
    object SetExcursionistView : MainActions()
    object SetGuideView: MainActions()
}