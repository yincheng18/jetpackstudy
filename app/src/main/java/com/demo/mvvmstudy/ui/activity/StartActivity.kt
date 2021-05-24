package com.demo.mvvmstudy.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.demo.mvvmstudy.R
import com.demo.mvvmstudy.databinding.ActivityStartBinding
import com.library.common.base.BaseActivity
import com.library.common.mvvm.BaseViewModel

class StartActivity : BaseActivity<BaseViewModel<Any>, ActivityStartBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_start

    override fun getReplaceView(): View = mBinding.frameLayout

    override fun init(savedInstanceState: Bundle?) {

    }
}