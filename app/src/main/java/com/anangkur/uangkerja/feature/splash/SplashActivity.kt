package com.anangkur.uangkerja.feature.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.anangkur.uangkerja.R
import com.anangkur.uangkerja.base.BaseActivity
import com.anangkur.uangkerja.data.model.Result
import com.anangkur.uangkerja.feature.login.LoginActivity
import com.anangkur.uangkerja.feature.main.MainActivity
import com.anangkur.uangkerja.util.gone
import com.anangkur.uangkerja.util.obtainViewModel
import com.anangkur.uangkerja.util.visible
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.toast

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

        observeViewModel()
        openActivity()
    }

    private fun openActivity(){
        val handler = Handler()
        handler.postDelayed({
            if (mViewModel.isLoggedIn()){
                mViewModel.getConfigCoin()
            }else{
                MainActivity.startActivity(this)
                finish()
            }
        }, 3000)
    }

    private fun observeViewModel(){
        mViewModel.apply {
            resultConfigCoinLiveData.observe(this@SplashActivity, Observer {
                when (it.status){
                    Result.Status.LOADING -> {
                        pb_splash.visible()
                    }
                    Result.Status.SUCCESS -> {
                        pb_splash.gone()
                        saveConfigCoin(it.data?.data?.get(0)?.currency?:0)
                        MainActivity.startActivity(this@SplashActivity)
                        finish()
                    }
                    Result.Status.ERROR -> {
                        pb_splash.gone()
                        toast(it.message?:getString(R.string.message_default))
                        finish()
                    }
                }
            })
        }
    }
}
