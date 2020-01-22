package com.anangkur.uangkerja.feature.main.history

import android.view.View
import com.anangkur.uangkerja.R
import com.anangkur.uangkerja.base.BaseAdapter
import com.anangkur.uangkerja.base.BaseEndlessScrollAdapter
import com.anangkur.uangkerja.data.model.transaction.Transaction
import com.anangkur.uangkerja.data.model.transaction.Transaction.Jenis.ORDER
import com.anangkur.uangkerja.data.model.transaction.Transaction.Jenis.TOP_UP
import com.anangkur.uangkerja.data.model.transaction.Transaction.Status.*
import com.anangkur.uangkerja.data.model.transaction.TransactionApi
import kotlinx.android.synthetic.main.item_history_transaksi.view.*
import kotlinx.android.synthetic.main.item_progress.view.*

class HistoryTransaksiAdapter(private val listener: HistoryActionListener): BaseEndlessScrollAdapter<TransactionApi>(){

    override val layoutItem: Int
        get() = R.layout.item_history_transaksi
    override val layoutProgress: Int
        get() = R.layout.item_progress

    override fun bindItem(data: TransactionApi, itemView: View, position: Int) {
        itemView.tv_nominal_transaksi.text = data.coinAmount.toString()
        itemView.tv_id_transaksi.text = "id. ${data.id}"
        when (data.jenisEnum) {
            ORDER -> itemView.tv_jenis_transaksi.text = itemView.context.getString(R.string.label_jenistransaksi_order)
            TOP_UP -> itemView.tv_jenis_transaksi.text = itemView.context.getString(R.string.label_jenistransaksi_topup)
        }
        when (data.statusEnum) {
            SUCCESS -> itemView.tv_status_transaksi.text = itemView.context.getString(R.string.label_statustransaksi_sukses)
            CANCEL -> itemView.tv_status_transaksi.text = itemView.context.getString(R.string.label_statustransaksi_cancel)
            PENDING -> itemView.tv_status_transaksi.text = itemView.context.getString(R.string.label_statustransaksi_pending)
        }
        itemView.setOnClickListener { listener.onClickItem(data) }
    }

    override fun bindProgress(showProgress: Boolean, itemView: View) {
        itemView.pb_loadmore.visibility = if (showProgress){
            View.VISIBLE
        }else{
            View.GONE
        }
    }

}