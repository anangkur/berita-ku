package com.anangkur.uangkerja.feature.historyTransaction

import android.view.View
import com.anangkur.uangkerja.R
import com.anangkur.uangkerja.base.BaseAdapter
import com.anangkur.uangkerja.data.model.transaction.Transaction
import com.anangkur.uangkerja.data.model.transaction.Transaction.Jenis.ORDER
import com.anangkur.uangkerja.data.model.transaction.Transaction.Jenis.TOP_UP
import com.anangkur.uangkerja.data.model.transaction.Transaction.Status.*
import kotlinx.android.synthetic.main.item_history_transaksi.view.*

class HistoryTransaksiAdapter: BaseAdapter<Transaction>(){
    override val layout: Int
        get() = R.layout.item_history_transaksi

    override fun bind(data: Transaction, itemView: View, position: Int) {
        itemView.tv_nominal_transaksi.text = data.coinAmount
        itemView.tv_id_transaksi.text = "id. ${data.id}"
        when (data.jenis) {
            ORDER -> itemView.tv_jenis_transaksi.text = itemView.context.getString(R.string.label_jenistransaksi_order)
            TOP_UP -> itemView.tv_jenis_transaksi.text = itemView.context.getString(R.string.label_jenistransaksi_topup)
        }
        when (data.status) {
            SUCCESS -> itemView.tv_status_transaksi.text = itemView.context.getString(R.string.label_statustransaksi_sukses)
            CANCEL -> itemView.tv_status_transaksi.text = itemView.context.getString(R.string.label_statustransaksi_cancel)
            PENDING -> itemView.tv_status_transaksi.text = itemView.context.getString(R.string.label_statustransaksi_pending)
        }
    }

}