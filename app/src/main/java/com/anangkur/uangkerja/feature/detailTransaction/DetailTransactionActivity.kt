package com.anangkur.uangkerja.feature.detailTransaction

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import com.anangkur.uangkerja.R
import com.anangkur.uangkerja.base.BaseActivity
import com.anangkur.uangkerja.data.model.transaction.Transaction
import com.anangkur.uangkerja.util.obtainViewModel
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailTransactionActivity: BaseActivity<DetailTransactionViewModel>(), DetailTransactionActionListener {

    companion object {
        private const val EXTRA_TRANSACTION = "EXTRA_TRANSACTION"
        fun startActivity(context: Context, data: Transaction){
            context.startActivity(
                Intent(context, DetailTransactionActivity::class.java)
                    .putExtra(EXTRA_TRANSACTION, data)
            )
        }
    }

    override val mLayout: Int
        get() = R.layout.activity_detail_transaction
    override val mViewModel: DetailTransactionViewModel
        get() = obtainViewModel(DetailTransactionViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = toolbar
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_detail_transaction)

    override fun onClickCopy(value: String) {

    }

    override fun onClickPhotos() {

    }
}
