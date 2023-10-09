package com.prueba.flows.main.api.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BaseResponse(
    @SerializedName("success") var success: Boolean,
    @SerializedName("message") var message: String?,
    @SerializedName("status") var status: String?
) : Parcelable