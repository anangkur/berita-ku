package com.anangkur.beritaku.feature.home

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.anangkur.beritaku.R
import com.anangkur.beritaku.base.BaseActivity
import com.anangkur.beritaku.util.obtainViewModel

class HomeActivity: BaseActivity<HomeViewModel>() {

    override val mLayout: Int
        get() = R.layout.activity_home
    override val mViewModel: HomeViewModel
        get() = obtainViewModel(HomeViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = null
    override val mTitleToolbar: String?
        get() = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
