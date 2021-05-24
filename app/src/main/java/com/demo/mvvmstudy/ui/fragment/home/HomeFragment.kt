package com.demo.mvvmstudy.ui.fragment.home

import android.view.View
import com.demo.mvvmstudy.BigImageActivity
import com.demo.mvvmstudy.MeiZiData
import com.demo.mvvmstudy.R
import com.demo.mvvmstudy.databinding.FragmentHomeBinding
import com.library.common.base.BaseListFragment


class HomeFragment : BaseListFragment<HomeViewModel, FragmentHomeBinding,
        MeiZiAdapter, MeiZiData>() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun getReplaceView(): View = mBinding.fragmentHome

    override fun initRecyclerView() {
        mSmartRefreshLayout = mBinding.smartRefreshLayout
        mRecyclerView = mBinding.recyclerView
        mAdapter = MeiZiAdapter()
        mAdapter.animationEnable = true
        mAdapter.setOnItemClickListener { _, _, position ->
            startActivity(
                android.content.Intent(
                    mContext,
                    BigImageActivity::class.java
                ).putExtra("item", mAdapter.getItem(position))
            )
        }
        loadPageListData(1)

    }

    override fun loadPageListData(pageNo: Int, pageSize: Int) {
        mViewModel.discoverHot(pageNo, pageSize)
    }
}