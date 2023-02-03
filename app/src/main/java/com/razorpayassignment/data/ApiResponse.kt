package com.razorpayassignment.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiResponse(

    @SerializedName("logo-url") var logoUrl: String? = null,
    @SerializedName("heading-text") var headingText: String? = null,
    @SerializedName("uidata") var uiData: ArrayList<UIData> = arrayListOf()

):Parcelable