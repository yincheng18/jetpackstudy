package com.demo.mvvmstudy.ui.activity.main

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demo.mvvmstudy.R
import com.demo.mvvmstudy.databinding.ActivityMainBinding
import com.demo.mvvmstudy.ui.fragment.home.HomeFragment
import com.demo.mvvmstudy.ui.fragment.mine.MineFragment
import com.demo.mvvmstudy.ui.fragment.sort.SortFragment
import com.library.common.base.BaseActivity
import com.library.common.utils.clickDelay
import kotlin.collections.ArrayList

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private var systemTime: Long = 0

    private var textList = ArrayList<TextView>()

    override fun onBackPressed() {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - systemTime > 2000) {
            showToast("再按一次返回键退出")
            systemTime = currentTimeMillis
            return
        }
        finish()
    }

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getReplaceView(): View = mBinding.llMain

    override fun init(savedInstanceState: Bundle?) {
        initViewPager()
        mBinding.tvThree.clickDelay {
            mBinding.viewPager.setCurrentItem(2, false)
            textSelect(mBinding.tvThree)
        }
        mBinding.tvOne.clickDelay {
            mBinding.viewPager.setCurrentItem(0, false)
            textSelect(mBinding.tvOne)
        }
        mBinding.tvTwo.clickDelay {
            mBinding.viewPager.setCurrentItem(1, false)
            textSelect(mBinding.tvTwo)
        }
    }

    private fun textSelect(text: TextView) {
        for (textView in textList) {
            textView.isSelected = text == textView
        }
    }

    private fun initViewPager() {
        textList.run {
            add(mBinding.tvOne)
            add(mBinding.tvTwo)
            add(mBinding.tvThree)
        }
        val list = ArrayList<Fragment>()
        list.run {
            add(HomeFragment())
            add(SortFragment())
            add(MineFragment())
        }
        mBinding.viewPager.offscreenPageLimit = list.size - 1
        mBinding.viewPager.isUserInputEnabled = false
        mBinding.viewPager.adapter = MyFragmentStateAdapter(this, list)
        textSelect(mBinding.tvOne)
    }

    class MyFragmentStateAdapter(
        fragmentActivity: FragmentActivity,
        private val list: List<Fragment>
    ) :
        FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            return list[position]
        }

        override fun getItemCount(): Int {
            return list.size
        }
    }
}