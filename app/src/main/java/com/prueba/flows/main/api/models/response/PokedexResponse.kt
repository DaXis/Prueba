package com.prueba.flows.main.api.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokedexResponse(
    @SerializedName("count") var count: Int,
    @SerializedName("next") var next: String?,
    @SerializedName("results") var results: List<PokedexResult>?
) : Parcelable

@Parcelize
data class PokedexResult(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String?
) : Parcelable