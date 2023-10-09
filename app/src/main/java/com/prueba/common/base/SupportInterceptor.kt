package com.prueba.common.base

import com.prueba.common.base.BaseConfig.Companion.SELECTED_URL
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl

class SupportInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = if (BaseConfig.HAVE_ALTERNATIVE_URL) {
            val baseUrl = getAlternativeUrl()
                ?: request.url.newBuilder().build()

            request.url.newBuilder()
                .scheme(baseUrl.scheme)
                .host(baseUrl.host)
                .port(baseUrl.port)
                .build()
        } else {
            request.url.newBuilder().build()
        }

        val requestBuilder = request.newBuilder()
            .url(newUrl)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "*/*")
            .build()

        return chain.proceed(requestBuilder)
    }

    private fun getAlternativeUrl() = when (SELECTED_URL) {
        0 -> BaseConfig.POKE_URL?.toHttpUrl()
        1 -> BaseConfig.LOCATION_URL?.toHttpUrl()
        else -> BaseConfig.BASE_URL.toHttpUrl()
    }
}