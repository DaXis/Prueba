package com.prueba.flows.main.api

import com.prueba.flows.main.api.models.response.BaseResponse
import com.prueba.utils.ApiConstants.IMAGE_FAIL_URL
import com.prueba.utils.ApiConstants.IMAGE_OK_URL
import com.prueba.utils.ApiConstants.POKE_DETAIL_URL
import com.prueba.utils.ApiConstants.POKE_LIST_URL

import io.reactivex.Single
import retrofit2.http.*

interface PruebaServicesApi {

    @POST(IMAGE_OK_URL)
    fun consumeOKImage(): Single<BaseResponse>

    @POST(IMAGE_FAIL_URL)
    fun consumeFailImage(): Single<BaseResponse>

    @POST(POKE_LIST_URL)
    fun consumePokeList(): Single<BaseResponse>

    @POST(POKE_DETAIL_URL)
    fun consumePokeDetail(): Single<BaseResponse>

}