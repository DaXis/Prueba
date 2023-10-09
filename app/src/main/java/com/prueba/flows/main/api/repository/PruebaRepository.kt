package com.prueba.flows.main.api.repository

import com.prueba.flows.main.api.PruebaServicesApi
import com.prueba.utils.applySchedulers
import dagger.Reusable
import javax.inject.Inject

@Reusable
class PruebaRepository @Inject constructor(
    private val processApi: PruebaServicesApi
) {

    fun consumeFlowManager() =
        processApi.consumeOKImage().applySchedulers()

    fun consumeFailImage() =
        processApi.consumeFailImage().applySchedulers()

    fun consumePokeList() =
        processApi.consumePokeList().applySchedulers()

    fun consumePokeDetail() =
        processApi.consumePokeDetail().applySchedulers()

    private companion object {

    }
}