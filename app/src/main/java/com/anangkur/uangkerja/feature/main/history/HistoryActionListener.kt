package com.anangkur.uangkerja.feature.main.history

import com.anangkur.uangkerja.data.model.transaction.Transaction

interface HistoryActionListener {
    fun onClickItem(item: Transaction)
}