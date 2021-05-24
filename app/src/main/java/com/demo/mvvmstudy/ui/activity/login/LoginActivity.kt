package com.demo.mvvmstudy.ui.activity.login
import android.os.Bundle
import android.view.View
import com.demo.mvvmstudy.R
import com.demo.mvvmstudy.databinding.ActivityLoginBinding
import com.library.common.base.BaseActivity
import com.library.common.mvvm.BaseViewModel


class LoginActivity : BaseActivity<BaseViewModel<Any>,ActivityLoginBinding>() {
    override fun getLayoutId(): Int=R.layout.activity_login

    override fun getReplaceView(): View =mBinding.main


    override fun init(savedInstanceState: Bundle?) {

    }

}