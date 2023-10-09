package com.prueba.utils

import androidx.core.text.HtmlCompat

object UtilsExtensions {
    fun Int?.orZero(): Int = this ?: 0
    fun Boolean?.orFalse(): Boolean = this ?: false
    fun Boolean?.orTrue(): Boolean = this ?: true

    fun String?.isNotNullOrEmpty(): Boolean {
        return this.isNullOrEmpty().not()
    }

    fun String?.toToken(): String {
        return "Bearer $this"
    }

    fun String?.defaultCountry(): String = this ?: "México"

    fun String?.toHtml() = HtmlCompat.fromHtml(this.orEmpty(), HtmlCompat.FROM_HTML_MODE_LEGACY)

    fun String?.toParse(): String {
        return if (this.isNullOrEmpty()) {
            "0"
        } else {
            this
        }
    }

    fun String?.toRFC(): String {
        return if (this.orEmpty().length >= 10) {
            this.orEmpty().substring((IntRange(0, 9)))
        } else {
            this.orEmpty()
        }
    }
}