package com.anangkur.uangkerja.feature.main.history

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.anangkur.uangkerja.R
import com.anangkur.uangkerja.base.BaseFragment
import com.anangkur.uangkerja.data.model.transaction.Transaction
import com.anangkur.uangkerja.feature.detailTransaction.DetailTransactionActivity
import com.anangkur.uangkerja.util.obtainViewModel
import com.anangkur.uangkerja.util.setupRecyclerViewLinear
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment: BaseFragment<HistoryTransaksiViewModel>(), HistoryActionListener{

    override val mLayout: Int
        get() = R.layout.fragment_history
    override val mViewModel: HistoryTransaksiViewModel?
        get() = obtainViewModel(HistoryTransaksiViewModel::class.java)

    companion object{
        fun newInstance() = HistoryFragment()
    }

    private lateinit var mAdapter: HistoryTransaksiAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupDummyHistory()
        swipe_history.setOnRefreshListener { setupDummyHistory() }
    }

    private fun setupAdapter(){
        mAdapter =
            HistoryTransaksiAdapter(this)
        recycler_history.apply {
            setupRecyclerViewLinear(requireContext(), LinearLayoutManager.VERTICAL)
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
        swipe_history.isRefreshing = false
    }

    override fun onClickItem(item: Transaction) {
        DetailTransactionActivity.startActivity(requireContext(), item)
    }
}