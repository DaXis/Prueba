package com.prueba.utils

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BaseErrorResponse(
    @SerializedName("success") var success: Boolean?,
    @SerializedName("error") var error: String?,
    @SerializedName("code") var code: String?,
    @SerializedName("timestamp") var timestamp: String?,
    @SerializedName("status") var status: String?,
    @SerializedName("message") var message: String?
) : Parcelable