package com.prueba.common.base

import android.content.Context
import android.content.SharedPreferences
import com.prueba.utils.UtilsExtensions.orFalse

open class BasePreferences {

    private var sharedPreferences: SharedPreferences? = null

    fun init(context: Context, fileName: String): SharedPreferences? {
        sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPreferences
    }

    operator fun set(key: String, value: String): BasePreferences {
        sharedPreferences?.edit()?.putString(key, value)?.apply()
        return this
    }

    operator fun get(key: String): String = sharedPreferences?.getString(key, null).orEmpty()

    operator fun set(key: String, value: Boolean): BasePreferences {
        sharedPreferences?.edit()?.putBoolean(key, value)?.apply()
        return this
    }

    fun getBoolean(key: String): Boolean = sharedPreferences?.getBoolean(key, false).orFalse()
}