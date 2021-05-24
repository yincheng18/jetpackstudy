package com.demo.mvvmstudy.ui.fragment.mine

import android.view.View
import androidx.databinding.DataBindingUtil
import com.demo.mvvmstudy.R
import com.demo.mvvmstudy.databinding.FragmentMineBinding
import com.demo.mvvmstudy.databinding.LayoutAdapterMineBinding
import com.library.common.base.BaseAdapter
import com.library.common.base.BaseListFragment
import com.library.common.view.baseviewholder.CommonViewHolder
import java.util.ArrayList

/**
 * @author yin
 * @desc
 * @time 2021/4/29
 */
class MineFragment :
    BaseListFragment<MineViewModel, FragmentMineBinding, MineFragment.MineAdapter, String>() {
    override fun getLayoutId(): Int = R.layout.fragment_mine

    class MineAdapter : BaseAdapter<String>(R.layout.layout_adapter_mine, ArrayList()) {

        override fun onItemViewHolderCreated(viewHolder: CommonViewHolder, viewType: Int) {
            DataBindingUtil.bind<LayoutAdapterMineBinding>(viewHolder.itemView)
        }

        override fun convert(helper: CommonViewHolder, item: String) {
            val mBinding = helper.getBinding<LayoutAdapterMineBinding>()
            mBinding?.let {
                it.tvName.text = item
            }
        }
    }

    override fun getReplaceView(): View = mBinding.frameLayout

    override fun initRecyclerView() {
        mSmartRefreshLayout = mBinding.smartRefreshLayout
        mRecyclerView = mBinding.recyclerView
        mAdapter = MineAdapter()
        val list = ArrayList<String>()
        for (index in 0..20) {
            list.add(index.toString())
        }
        showListData(list, 1)
    }

    override fun loadPageListData(pageNo: Int, pageSize: Int) {
        val list = ArrayList<String>()
        val size=mAdapter.data.size
        for (index in size..size+20) {
            list.add(index.toString())
        }
        showListData(list, pageNo)
    }
}