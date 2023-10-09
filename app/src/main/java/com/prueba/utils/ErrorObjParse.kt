package com.prueba.utils

import com.google.gson.Gson

fun parseError(json: String): BaseErrorResponse? {
    val gson = Gson()
    return gson.fromJson(json, BaseErrorResponse::class.java)
}