package com.anangkur.uangkerja.feature.detailTransaction

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.uangkerja.data.Repository
import com.anangkur.uangkerja.util.Const.COMPRESS_QUALITY
import com.anangkur.uangkerja.util.Const.MAX_IMAGE_SIZE
import com.anangkur.uangkerja.util.Const.SAMPLE_SIZE
import com.anangkur.uangkerja.util.fileSizeInKB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class DetailTransactionViewModel(private val repository: Repository): ViewModel(){

    val isCompressing = MutableLiveData<Boolean>()
    val eventCompressSuccess = MutableLiveData<File>()
    val eventMessage = MutableLiveData<String>()

    fun compressFileImage(imageFile: File) {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                isCompressing.value = true
                eventCompressSuccess.value = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) { suspendCompressFileImage(imageFile) }
            } catch (e: Exception) {
                eventMessage.value = e.message
            } finally {
                isCompressing.value = false
            }
        }
    }

    private suspend fun suspendCompressFileImage(imageFile: File): File {
        Log.d("IMAGE_COMPRESS", "IMAGE_COMPRESS: size: ${imageFile.fileSizeInKB}, sampleSize: $SAMPLE_SIZE, quality: $COMPRESS_QUALITY")

        return if (imageFile.fileSizeInKB > MAX_IMAGE_SIZE) {
            val options = BitmapFactory.Options()
            options.inSampleSize = SAMPLE_SIZE
            val bitmap = BitmapFactory.decodeFile(imageFile.path, options)
            bitmap.compress(Bitmap.CompressFormat.JPEG, COMPRESS_QUALITY, FileOutputStream(imageFile))

            // recursively if needed more than once
            suspendCompressFileImage(imageFile)
        } else {
            imageFile
        }
    }
}