package com.prueba.common.base

import com.prueba.BuildConfig.ENDPOINT

class BaseConfig {
    companion object {
        var BASE_URL: String = ENDPOINT
        var LOCATION_URL: String? = com.prueba.BuildConfig.LOCATION_URL
        var USE_ALTERNATIVE_URL: Boolean = false
        var ENABLE_SSL_VALIDATION: Boolean = true
        var HAVE_ALTERNATIVE_URL: Boolean = true
        var SELECTED_URL: Int = 0
    }
}