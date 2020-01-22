package com.anangkur.uangkerja.data.model.profile

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("role_id")
    val roleId: Int = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("email")
    val email: String = "",

    @SerializedName("avatar")
    val avatar: String = "",

    @SerializedName("settings")
    val settings: Any = Any(),

    @SerializedName("token")
    val token: Any = Any(),

    @SerializedName("coin")
    val coin: Int = 0,

    @SerializedName("point_reward")
    val pointReward: Int = 0,

    @SerializedName("point_fee")
    val pointFee: Int = 0,

    @SerializedName("parent_id")
    val parentId: Int = 0,

    @SerializedName("ayah_id")
    val ayahId: Int = 0,

    @SerializedName("status")
    val status: Int = 0,

    @SerializedName("created_at")
    val createdAt: String = "",

    @SerializedName("updated_at")
    val updatedAt: String = ""
)