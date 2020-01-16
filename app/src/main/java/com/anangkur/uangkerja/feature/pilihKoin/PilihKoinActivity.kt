package com.anangkur.uangkerja.feature.pilihKoin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.Toolbar
import com.anangkur.uangkerja.R
import com.anangkur.uangkerja.base.BaseActivity
import com.anangkur.uangkerja.feature.reviewTransaction.ReviewTransakiActivity
import com.anangkur.uangkerja.util.obtainViewModel
import kotlinx.android.synthetic.main.activity_pilih_koin.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class PilihKoinActivity: BaseActivity<PilihKoinViewModel>(), PilihKoinActionListener {

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, PilihKoinActivity::class.java))
        }
    }

    override val mLayout: Int
        get() = R.layout.activity_pilih_koin
    override val mViewModel: PilihKoinViewModel
        get() = obtainViewModel(PilihKoinViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = toolbar
    override val mTitleToolbar: String?
        get() = getString(R.string.toolbar_pilih_koin)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupEdittextKoin()
        btn_30.setOnClickListener { this.onClickPlusKoin(30) }
        btn_10.setOnClickListener { this.onClickPlusKoin(10) }
        btn_5.setOnClickListener { this.onClickPlusKoin(5) }
        btn_submit.setOnClickListener { this.onClickSubmit(mViewModel.actualCoin) }
    }

    private fun setupEdittextKoin(){
        et_koin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isEmpty()) {
                    et_koin.setText("0")
                }else if (s.length > 1 && s.startsWith("0")){
                    et_koin.setText(s.substring(1))
                    et_koin.text?.let { et_koin.setSelection(it.length) }
                }
                mViewModel.actualCoin = et_koin.text.toString().toInt()
            }
        })
    }

    override fun onClickPlusKoin(plus: Int) {
        mViewModel.actualCoin += plus
        et_koin.setText(mViewModel.actualCoin.toString())
    }

    override fun onClickSubmit(actualCoin: Int) {
        ReviewTransakiActivity.startActivity(this)
    }
}
