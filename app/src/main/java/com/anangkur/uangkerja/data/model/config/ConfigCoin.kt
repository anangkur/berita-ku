package com.anangkur.uangkerja.data.model.config

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConfigCoin(
    
    @SerializedName("id")
    val id: Int = 0,
    
    @SerializedName("currency")
    val currency: Int = 0,
    
    @SerializedName("updated_at")
    val updatedAt: String = "",
    
    @SerializedName("created_at")
    val createdAt: String = ""
): Parcelable