package com.prueba.utils

import android.content.Context
import com.prueba.common.base.BasePreferences
import com.prueba.utils.PrefsConstants.PREFS_TAG
import javax.inject.Inject

class PruebaPreferencesManager @Inject constructor(
    context: Context
) : BasePreferences() {

    init {
        init(context, PREFS_TAG)
    }

}