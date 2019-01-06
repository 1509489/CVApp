package com.pixelart.cvapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CareerSummary(
    val from: String,
    val to: String,
    val outline: String,
    @SerializedName("key_responsibilities")
    val keyResponsibilities: List<String>
):Parcelable