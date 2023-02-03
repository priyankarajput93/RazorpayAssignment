package com.razorpayassignment.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIData (

    @SerializedName("uitype" ) var uitype : String? = null,
    @SerializedName("value"  ) var value  : String? = null,
    @SerializedName("key") var key    : String? = null,
    @SerializedName("hint") var hint    : String? = null

):Parcelable
