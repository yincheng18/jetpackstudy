package com.demo.mvvmstudy

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.demo.mvvmstudy.databinding.ActivityBigImageBinding
import com.demo.mvvmstudy.databinding.ActivityMainBinding

import com.library.common.base.BaseActivity
import com.library.common.mvvm.BaseViewModel
import kotlin.properties.Delegates

class BigImageActivity : BaseActivity<BaseViewModel<Any>, ActivityBigImageBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_big_image
    override fun getReplaceView(): View = mBinding.llMain

    override fun init(savedInstanceState: Bundle?) {
        val meiZi = intent.getSerializableExtra("item") as MeiZiData
        meiZi.run {
            Glide.with(mBinding.ivImage).load(meiZi.url).into(mBinding.ivImage)
            mBinding.tvTitle.text = desc
        }
    }
}