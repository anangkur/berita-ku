package com.anangkur.uangkerja.feature.main.history

import com.anangkur.uangkerja.data.model.transaction.TransactionApi

interface HistoryActionListener {
    fun onClickItem(item: TransactionApi)
}