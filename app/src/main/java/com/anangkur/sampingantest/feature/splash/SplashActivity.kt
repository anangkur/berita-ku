package com.anangkur.sampingantest.feature.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.widget.Toolbar
import com.anangkur.sampingantest.R
import com.anangkur.sampingantest.base.BaseActivity
import com.anangkur.sampingantest.util.obtainViewModel

class SplashActivity: BaseActivity<SplashViewModel>() {
    override val mLayout: Int
        get() = R.layout.activity_splash
    override val mViewModel: SplashViewModel
        get() = obtainViewModel(SplashViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = null
    override val mTitleToolbar: String?
        get() = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        openActivity()
    }

    private fun openActivity(){
        val handler = Handler()
        handler.postDelayed({
            finish()
        }, 3000)
    }
}
