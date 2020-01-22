package com.anangkur.uangkerja.data.model.transaction

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionApi(

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("coin_amount")
    val coinAmount: Int = 0,

    @SerializedName("date")
    val date: String = "",

    @SerializedName("status")
    val status: String = "",

    @SerializedName("photo")
    val photo: String = "",

    @SerializedName("bank_name")
    val bankName: String = "",

    @SerializedName("account_name")
    val accountName: String = "",

    @SerializedName("account_number")
    val accountNumber: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("user_id")
    val userId: Int = 0,

    @SerializedName("user_name")
    val userName: String = "",

    val jenisEnum: Jenis = Jenis.TOP_UP,

    val statusEnum: Status = Status.PENDING

    ): Parcelable {
    enum class Jenis{
        ORDER, TOP_UP
    }

    enum class Status{
        PENDING, SUCCESS, CANCEL
    }
}