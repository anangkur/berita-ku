package com.anangkur.uangkerja.feature.detailTransaction

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.uangkerja.R
import com.anangkur.uangkerja.base.BaseActivity
import com.anangkur.uangkerja.base.DialogImagePickerActionListener
import com.anangkur.uangkerja.data.model.transaction.Transaction
import com.anangkur.uangkerja.util.*
import com.esafirm.imagepicker.features.ImagePicker
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_detail_transaction.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.toast
import java.io.File

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            cropImage(data)
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            handleImageCropperResult(data, resultCode)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
        layout_upload_photo.setOnClickListener { this.onClickPhotos() }
    }

    override fun onClickCopy(value: String) {
        showToastShort(getString(R.string.message_copy_to_clipboard))
        this.copyToClipboard(value)
    }

    override fun onClickPhotos() {
        this.showDialogImagePicker(object: DialogImagePickerActionListener{
            override fun onClickCamera() {
                ImagePicker.cameraOnly().start(this@DetailTransactionActivity)
            }
            override fun onClickGallery() {
                ImagePicker.create(this@DetailTransactionActivity)
                    .single()
                    .showCamera(false)
                    .start()
            }
        })
    }

    private fun handleImageCropperResult(data: Intent?, resultCode: Int) {
        val image = CropImage.getActivityResult(data)
        if (resultCode == Activity.RESULT_OK) {
            mViewModel.compressFileImage(File(image.uri.path?:""))
        } else {
            toast(image.error.message ?: "")
        }
    }

    private fun observeViewModel(){
        mViewModel.apply {
            isCompressing.observe(this@DetailTransactionActivity, Observer {

            })
            eventCompressSuccess.observe(this@DetailTransactionActivity, Observer {
                iv_result.visible()
                iv_result.setImageURI(Uri.fromFile(it))
            })
            eventMessage.observe(this@DetailTransactionActivity, Observer {
                showSnackbarShort(it)
            })
        }
    }

    private fun cropImage(data: Intent?) {
        val image = ImagePicker.getFirstImageOrNull(data)
        CropImage.activity(Uri.fromFile(File(image.path)))
            .setGuidelines(CropImageView.Guidelines.OFF)
            .setFixAspectRatio(false)
            .start(this)
    }
}
