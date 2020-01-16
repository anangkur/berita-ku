package com.anangkur.uangkerja.feature.detailTransaction

import androidx.appcompat.widget.Toolbar
import com.anangkur.uangkerja.R
import com.anangkur.uangkerja.base.BaseActivity
import com.anangkur.uangkerja.util.obtainViewModel
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailTransactionActivity: BaseActivity<DetailTransactionViewModel>(), DetailTransactionActionListener {

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
