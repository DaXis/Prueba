package com.prueba.flows.main.api

import com.prueba.flows.main.api.models.response.PokeDetailResponse
import com.prueba.flows.main.api.models.response.PokedexResponse
import com.prueba.utils.ApiConstants.POKE_DETAIL_URL
import com.prueba.utils.ApiConstants.POKE_LIST_URL
import io.reactivex.Single
import retrofit2.http.*

interface PruebaServicesApi {

    @GET(POKE_LIST_URL)
    fun consumePokeList(
        @Query("limit") limit: String,
        @Query("offset") offset: String
    ): Single<PokedexResponse>

    @GET(POKE_DETAIL_URL)
    fun consumePokeDetail(
        @Path("number") number: String
    ): Single<PokeDetailResponse>
}