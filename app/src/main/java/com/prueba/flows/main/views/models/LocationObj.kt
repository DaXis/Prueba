package com.prueba.flows.main.views.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class LocationObj(
    var dateDay: String,
    var dateHour: String,
    var latitude: String,
    var longitude: String
) : Parcelable