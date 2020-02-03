package com.anangkur.beritaku.data.model.news

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Source(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = ""
): Parcelable