package com.anangkur.uangkerja.data.model.transaction

data class Transaction(
    val id: String = "",
    val userid: String = "",
    val jenis: Jenis,
    val coinAmount: String = "",
    val date: String = "",
    val status: Status
){
    enum class Jenis{
        ORDER, TOP_UP
    }

    enum class Status{
        PENDING, SUCCESS, CANCEL
    }
}