package com.pixelart.cvapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SampleCv(
    val name: String,
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val address: Address,
    val profile: String,
    @SerializedName("core_skills")
    val coreSkills: List<String>,
    @SerializedName("career_summary")
    val careerSummary: List<CareerSummary>,
    @SerializedName("education_qualifications")
    val educationQualifications: List<String>,
    val references: List<String>
):Parcelable