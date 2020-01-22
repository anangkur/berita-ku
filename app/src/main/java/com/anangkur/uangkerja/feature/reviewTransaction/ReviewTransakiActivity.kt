package com.anangkur.uangkerja.feature.reviewTransaction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.uangkerja.R
import com.anangkur.uangkerja.base.BaseActivity
import com.anangkur.uangkerja.base.BaseSpinnerListener
import com.anangkur.uangkerja.data.model.Result
import com.anangkur.uangkerja.data.model.transaction.Bank
import com.anangkur.uangkerja.data.model.transaction.Transaction
import com.anangkur.uangkerja.feature.detailTransaction.DetailTransactionActivity
import com.anangkur.uangkerja.util.*
import kotlinx.android.synthetic.main.activity_review_transaki.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class ReviewTransakiActivity: BaseActivity<ReviewTransaksiViewModel>(), ReviewTransaksiActionListener {

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, ReviewTransakiActivity::class.java))
        }
    }

    override val mLayout: Int
        get() = R.layout.activity_review_transaki
    override val mViewModel: ReviewTransaksiViewModel
        get() = obtainViewModel(ReviewTransaksiViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = toolbar
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_review_transaksi)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
        mViewModel.getListBank()
        btn_reload_bank.setOnClickListener { mViewModel.getListBank() }
        btn_lanjutkan.setOnClickListener { this.onClickLanjutkan() }
    }

    private fun observeViewModel(){
        mViewModel.apply {
            listBankLiveData.observe(this@ReviewTransakiActivity, Observer {
                when (it.status){
                    Result.Status.LOADING -> { pb_spinner_bank.visible() }
                    Result.Status.SUCCESS -> {
                        pb_spinner_bank.gone()
                        if (!it.data?.data.isNullOrEmpty()){
                            createListString(it.data?.data!!)
                        }
                    }
                    Result.Status.ERROR -> {
                        pb_spinner_bank.gone()
                        this@ReviewTransakiActivity.showSnackbarShort(it.message?:getString(R.string.error_default))
                    }
                }
            })
            listBankStringLive.observe(this@ReviewTransakiActivity, Observer {
                setupSpinnerBank(it)
            })
        }
    }

    private fun setupSpinnerBank(data: ArrayList<String>){
        spinner_pilih_bank.setupSpinner(data, object: BaseSpinnerListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mViewModel.selectedBank = mViewModel.getBankSelected(position)
                if (mViewModel.selectedBank != null){
                    setupBankView(mViewModel.selectedBank!!)
                }
            }
        })
    }

    private fun setupBankView(data: Bank){
        tv_nama_bank.text = data.bankName
        tv_norek.text = data.accountNumber
        tv_atas_nama.text = data.accountName
    }

    override fun onClickLanjutkan() {
        DetailTransactionActivity.startActivity(this, Transaction())
    }
}
