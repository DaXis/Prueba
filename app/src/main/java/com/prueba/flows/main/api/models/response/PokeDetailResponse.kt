package com.prueba.flows.main.api.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokeDetailResponse(
    @SerializedName("types") var types: List<TypesObj>,
    @SerializedName("abilities") var abilities: List<AbilitiesObj>,
    @SerializedName("height") var height: Int,
    @SerializedName("id") var id: Int,
    @SerializedName("order") var order: Int,
    @SerializedName("weight") var weight: Int
) : Parcelable

@Parcelize
data class TypesObj(
    @SerializedName("slot") var slot: Int,
    @SerializedName("type") var type: TypeObj
) : Parcelable

@Parcelize
data class TypeObj(
    @SerializedName("name") var mane: String
) : Parcelable

@Parcelize
data class AbilitiesObj(
    @SerializedName("ability") var ability: AbilitieObj,
    @SerializedName("is_hidden") var isHidden: Boolean,
    @SerializedName("slot") var slot: Int
) : Parcelable

@Parcelize
data class AbilitieObj(
    @SerializedName("name") var mane: String,
) : Parcelable
