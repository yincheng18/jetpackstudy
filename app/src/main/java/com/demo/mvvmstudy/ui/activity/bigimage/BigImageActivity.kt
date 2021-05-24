package com.demo.mvvmstudy.ui.activity.bigimage

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.demo.mvvmstudy.R
import com.demo.mvvmstudy.bean.MeiZiData
import com.demo.mvvmstudy.databinding.ActivityBigImageBinding

import com.library.common.base.BaseActivity
import com.library.common.mvvm.BaseViewModel
import com.library.common.utils.GlideUtils

class BigImageActivity : BaseActivity<BaseViewModel<Any>, ActivityBigImageBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_big_image
    override fun getReplaceView(): View = mBinding.llMain

    override fun init(savedInstanceState: Bundle?) {
        initImmersionBar(R.color.black)
        val meiZi = intent.getSerializableExtra("item") as MeiZiData
        meiZi.run {
            GlideUtils.loadImage(mBinding.ivImage, url,GlideUtils.optionsNormal)
            mBinding.tvTitle.text = desc
        }
    }
}