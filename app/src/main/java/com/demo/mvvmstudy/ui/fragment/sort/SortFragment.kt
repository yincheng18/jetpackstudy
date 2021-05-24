package com.demo.mvvmstudy.ui.fragment.sort

import android.os.Bundle
import android.view.View
import com.demo.mvvmstudy.R
import com.demo.mvvmstudy.databinding.FragmentSortBinding
import com.library.common.base.BaseFragment

class SortFragment : BaseFragment<SortViewModel,FragmentSortBinding>() {

    override fun getLayoutId(): Int =R.layout.fragment_sort

    override fun getReplaceView(): View =mBinding.frameLayout

    override fun init(savedInstanceState: Bundle?) {

    }
}