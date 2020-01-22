package com.anangkur.uangkerja.feature.main.history

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anangkur.uangkerja.R
import com.anangkur.uangkerja.base.BaseFragment
import com.anangkur.uangkerja.data.model.Result
import com.anangkur.uangkerja.data.model.transaction.TransactionApi
import com.anangkur.uangkerja.feature.detailTransaction.DetailTransactionActivity
import com.anangkur.uangkerja.util.*
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment: BaseFragment<HistoryTransaksiViewModel>(), HistoryActionListener{

    override val mLayout: Int
        get() = R.layout.fragment_history
    override val mViewModel: HistoryTransaksiViewModel
        get() = obtainViewModel(HistoryTransaksiViewModel::class.java)

    companion object{
        fun newInstance() = HistoryFragment()
    }

    private lateinit var mAdapter: HistoryTransaksiAdapter
    private lateinit var endlessOnScrollListener: EndlessOnScrollListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createScrollListener()
        setupAdapter()
        observeViewModel()
        mViewModel.getHistoryTransaction(1)
        endlessOnScrollListener.mLoading = true
        swipe_history.setOnRefreshListener {
            mViewModel.getHistoryTransaction(1)
            endlessOnScrollListener.mLoading = true
        }
    }

    private fun createScrollListener(){
        endlessOnScrollListener =  object: EndlessOnScrollListener(true){
            override fun onLoadMore() {
                mViewModel.getHistoryTransaction(mViewModel.nextPage.plus(1))
            }
        }
    }

    private fun setupAdapter(){
        mAdapter = HistoryTransaksiAdapter(this)
        recycler_history.apply {
            setupRecyclerViewLinear(requireContext(), LinearLayoutManager.VERTICAL)
            adapter = mAdapter
            addOnScrollListener(endlessOnScrollListener)
        }
    }

    private fun observeViewModel(){
        mViewModel.apply {
            resultListHistoryTransactionLiveData.observe(this@HistoryFragment, Observer {
                when(it.status){
                    Result.Status.LOADING -> {
                        swipe_history.startLoading()
                    }
                    Result.Status.SUCCESS -> {
                        swipe_history.stopLoading()
                        if (it.data?.data != null){
                            paginateData(it.data.data)
                        }
                    }
                    Result.Status.ERROR -> {
                        swipe_history.stopLoading()
                        mAdapter.showProgress(false, positionStart, differentCount)
                        view?.showSnackbarShort(it.message?:getString(R.string.error_default))
                    }
                }
            })
            loadMoreLive.observe(this@HistoryFragment, Observer {
                mAdapter.showProgress(it, positionStart, differentCount)
                endlessOnScrollListener.mLoading = it
            })
            listHistoryTransactionLiveData.observe(this@HistoryFragment, Observer {
                mAdapter.setRecyclerData(it, positionStart, differentCount)
            })
        }
    }

    override fun onClickItem(item: TransactionApi) {
        DetailTransactionActivity.startActivity(requireContext(), item)
    }
}