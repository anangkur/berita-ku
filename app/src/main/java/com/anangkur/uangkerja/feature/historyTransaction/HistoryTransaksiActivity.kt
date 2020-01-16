package com.anangkur.uangkerja.feature.historyTransaction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.anangkur.uangkerja.R
import com.anangkur.uangkerja.base.BaseActivity
import com.anangkur.uangkerja.data.model.transaction.Transaction
import com.anangkur.uangkerja.util.obtainViewModel
import com.anangkur.uangkerja.util.setupRecyclerViewLinear
import kotlinx.android.synthetic.main.activity_history_transaksi.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class HistoryTransaksiActivity: BaseActivity<HistoryTransaksiViewModel>() {

    companion object {
        fun startActivity(context: Context){
            context.startActivity(Intent(context, HistoryTransaksiActivity::class.java))
        }
    }

    override val mLayout: Int
        get() = R.layout.activity_history_transaksi
    override val mViewModel: HistoryTransaksiViewModel
        get() = obtainViewModel(HistoryTransaksiViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = toolbar
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_history_transaksi)

    private lateinit var mAdapter: HistoryTransaksiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupAdapter()
        setupDummyHistory()
    }

    private fun setupAdapter(){
        mAdapter = HistoryTransaksiAdapter()
        recycler_history.apply {
            setupRecyclerViewLinear(this@HistoryTransaksiActivity, LinearLayoutManager.VERTICAL)
            adapter = mAdapter
        }
    }

    private fun setupDummyHistory(){
        val dummyData = ArrayList<Transaction>()
        dummyData.add(Transaction("ABC/123/DEF", "", Transaction.Jenis.TOP_UP, "Rp 100.000", "", Transaction.Status.PENDING))
        dummyData.add(Transaction("ABC/123/DEF", "", Transaction.Jenis.TOP_UP, "Rp 100.000", "", Transaction.Status.PENDING))
        dummyData.add(Transaction("ABC/123/DEF", "", Transaction.Jenis.TOP_UP, "Rp 100.000", "", Transaction.Status.PENDING))
        dummyData.add(Transaction("ABC/123/DEF", "", Transaction.Jenis.TOP_UP, "Rp 100.000", "", Transaction.Status.PENDING))
        dummyData.add(Transaction("ABC/123/DEF", "", Transaction.Jenis.TOP_UP, "Rp 100.000", "", Transaction.Status.PENDING))
        dummyData.add(Transaction("ABC/123/DEF", "", Transaction.Jenis.TOP_UP, "Rp 100.000", "", Transaction.Status.PENDING))
        dummyData.add(Transaction("ABC/123/DEF", "", Transaction.Jenis.TOP_UP, "Rp 100.000", "", Transaction.Status.PENDING))
        dummyData.add(Transaction("ABC/123/DEF", "", Transaction.Jenis.TOP_UP, "Rp 100.000", "", Transaction.Status.PENDING))
        dummyData.add(Transaction("ABC/123/DEF", "", Transaction.Jenis.TOP_UP, "Rp 100.000", "", Transaction.Status.PENDING))
        dummyData.add(Transaction("ABC/123/DEF", "", Transaction.Jenis.TOP_UP, "Rp 100.000", "", Transaction.Status.PENDING))
        mAdapter.setRecyclerData(dummyData)
    }
}
