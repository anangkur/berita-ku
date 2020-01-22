package com.anangkur.uangkerja.data.model.transaction

import com.google.gson.annotations.SerializedName

data class Bank(
    
    @SerializedName("id")
    val id: Int = 0,
    
    @SerializedName("bank_name")
    val bankName: String = "",
    
    @SerializedName("account_name")
    val accountName: String = "",
    
    @SerializedName("account_number")
    val accountNumber: String = "",
    
    @SerializedName("description")
    val description: String = "",
    
    @SerializedName("updated_at")
    val updatedAt: String = "",
    
    @SerializedName("created_at")
    val createdAt: String = ""
)