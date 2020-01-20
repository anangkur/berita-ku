package com.anangkur.uangkerja.feature.reviewTransaction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.Toolbar
import com.anangkur.uangkerja.R
import com.anangkur.uangkerja.base.BaseActivity
import com.anangkur.uangkerja.base.BaseSpinnerListener
import com.anangkur.uangkerja.data.model.transaction.Bank
import com.anangkur.uangkerja.data.model.transaction.Transaction
import com.anangkur.uangkerja.feature.detailTransaction.DetailTransactionActivity
import com.anangkur.uangkerja.util.obtainViewModel
import com.anangkur.uangkerja.util.setupSpinner
import com.anangkur.uangkerja.util.showToastShort
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

        setupSpinnerBank()
        btn_lanjutkan.setOnClickListener { this.onClickLanjutkan() }
    }

    private fun setupSpinnerBank(){
        val dummyBank = ArrayList<String>()
        dummyBank.add("Mandiri")
        dummyBank.add("BRI")
        dummyBank.add("BNI")
        dummyBank.add("BCA")
        spinner_pilih_bank.setupSpinner(dummyBank, object: BaseSpinnerListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position){
                    0 -> setupBankView(Bank("Mandiri", "1234567890", "Anang Mandiri"))
                    1 -> setupBankView(Bank("BRI", "1234567890", "Anang BRI"))
                    2 -> setupBankView(Bank("BNI", "1234567890", "Anang BNI"))
                    3 -> setupBankView(Bank("BCA", "1234567890", "Anang BCA"))
                }
            }
        })
    }

    private fun setupBankView(data: Bank){
        tv_nama_bank.text = data.bankName
        tv_norek.text = data.bankNumber
        tv_atas_nama.text = data.bankAccountName
    }

    override fun onClickLanjutkan() {
        DetailTransactionActivity.startActivity(this,
            Transaction(
                "ABC/123/DEF",
                "",
                Transaction.Jenis.TOP_UP,
                "Rp 100.000",
                "",
                Transaction.Status.PENDING)
        )
    }
}
