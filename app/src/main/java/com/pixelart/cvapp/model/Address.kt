package com.pixelart.cvapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    val street: String,
    val town: String,
    @SerializedName("post_code")
    val postCod: String
):Parcelable