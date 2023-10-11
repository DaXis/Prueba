package com.prueba.flows.main.api.repository

import com.prueba.flows.main.api.PruebaServicesApi
import com.prueba.utils.applySchedulers
import dagger.Reusable
import javax.inject.Inject

@Reusable
class PruebaRepository @Inject constructor(
    private val processApi: PruebaServicesApi
) {

    fun consumePokeList(offset: String) =
        processApi.consumePokeList(
            LIMIT,
            offset
        ).applySchedulers()

    fun consumePokeDetail(number: String) =
        processApi.consumePokeDetail(
            number
        ).applySchedulers()

    private companion object {
        const val LIMIT = "25"
    }
}