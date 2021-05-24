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
        initView()
    }

    private fun initView() {
        mBinding.run {
            rgMain.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.rb_home -> {
                        viewPager.setCurrentItem(0,false)
                    }
                    R.id.rb_sort -> {
                        viewPager.setCurrentItem(1,false)
                    }
                    R.id.rb_mine -> {
                        viewPager.setCurrentItem(2,false)
                    }
                }
            }
            mBinding.rbHome.isChecked=true
        }
    }

    private fun initViewPager() {

        val list = ArrayList<Fragment>()
        list.run {
            add(HomeFragment())
            add(SortFragment())
            add(MineFragment())
        }
        mBinding.viewPager.offscreenPageLimit = list.size - 1
        mBinding.viewPager.isUserInputEnabled = false
        mBinding.viewPager.adapter = MyFragmentStateAdapter(this, list)

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