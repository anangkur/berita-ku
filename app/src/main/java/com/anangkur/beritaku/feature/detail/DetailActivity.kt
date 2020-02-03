package com.anangkur.beritaku.feature.detail

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.anangkur.beritaku.R
import com.anangkur.beritaku.base.BaseActivity
import com.anangkur.beritaku.util.obtainViewModel
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailActivity: BaseActivity<DetailViewModel>() {
    override val mLayout: Int
        get() = R.layout.activity_detail
    override val mViewModel: DetailViewModel
        get() = obtainViewModel(DetailViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = toolbar
    override val mTitleToolbar: String?
        get() = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
