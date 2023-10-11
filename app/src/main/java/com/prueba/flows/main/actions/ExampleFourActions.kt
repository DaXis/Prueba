package com.prueba.flows.main.actions

import com.prueba.flows.main.views.models.LocationObj

sealed class ExampleFourActions {
    object InitLocation : ExampleFourActions()
    data class RequestPermissions(val permissions: List<String>, val permissionCode: Int) : ExampleFourActions()
    data class GetData(val list: ArrayList<LocationObj>) : ExampleFourActions()
}