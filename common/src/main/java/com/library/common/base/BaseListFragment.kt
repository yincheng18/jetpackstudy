package com.library.common.base

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.androidadvance.topsnackbar.TSnackbar
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gyf.immersionbar.components.SimpleImmersionOwner
import com.gyf.immersionbar.components.SimpleImmersionProxy
import com.gyf.immersionbar.ktx.immersionBar
import com.library.common.R
import com.library.common.mvvm.BaseListViewModel
import com.library.common.mvvm.IListView
import com.library.common.utils.ListUtils
import com.library.common.view.IVaryViewHelperController
import com.library.common.view.LoadingDialog
import com.library.common.view.VaryViewHelperController
import com.library.common.view.baseviewholder.CommonViewHolder
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import java.lang.reflect.ParameterizedType

/**
 * BaseListFragment封装
 * 适用于单一列表接口，多操作接口。
 *
 * @author yangbw
 * @date 2020/9/1
 */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate")
abstract class BaseListFragment<VM : BaseListViewModel<*>, DB : ViewDataBinding,
        A : BaseQuickAdapter<T, CommonViewHolder>, T> : BaseFragment<VM, DB>(), IListView<T> {
    protected lateinit var mAdapter: A
    protected var mSmartRefreshLayout: SmartRefreshLayout? = null
    protected var mRecyclerView: RecyclerView? = null

    /**
     * list展示相关
     */
    protected var mPageNum = 1
    protected var mPageSize = 20
    protected var mLoadPageNum = 1 //当前正在加载的page，但是当前page接口还未做出响应
    private var mLoadMoreEnable = true

    override fun init(savedInstanceState: Bundle?) {
        registerDataChange()
        initRecyclerView()
        initRefreshLoadMore()
    }

    /**
     * 初始化View
     */
    abstract fun initRecyclerView()


    /**
     * 接口请求的数据变化
     */
    private fun registerDataChange() {
        mViewModel.mListData = MutableLiveData<T>()
        //数据变化的监听
        mViewModel.mListData?.observe(viewLifecycleOwner, {
            showListData(it as MutableList<T>, mPageNum)
        })
    }

    abstract fun loadPageListData(pageNo: Int, pageSize: Int = mPageSize)

    /**
     *
     * @param moreEnable 设置是否加载更多操作
     */
    open fun setLoadMoreEnable(moreEnable: Boolean) {
        mLoadMoreEnable = moreEnable
        mSmartRefreshLayout?.setEnableLoadMore(mLoadMoreEnable)
    }

    override fun showError(listener: View.OnClickListener?) {
        super.showError(listener)
        if (mRefreshEnable) {
            mSmartRefreshLayout?.isEnabled = false
        }
    }

    override fun showError(msg: String?, listener: View.OnClickListener?) {
        super.showError(msg, listener)
        if (mRefreshEnable) {
            mSmartRefreshLayout?.isEnabled = false
        }
    }
    override fun showEmpty(emptyMsg: String?, listener: View.OnClickListener?) {
        //加载当前page 出现List 为empty的情况--需要判断是否是第一页或者不是第一页
        if (mLoadPageNum > 1) {
            showNoMore()
        } else {
            mPageNum = 1
            mSmartRefreshLayout?.finishRefresh()
            mAdapter.data.clear()
            mViewController?.showEmpty(emptyMsg, listener)
            if (mRefreshEnable) {
                mSmartRefreshLayout?.isEnabled = false
            }
        }
    }
    /**
     * 设置刷新加载相关
     */
    private fun initRefreshLoadMore() {
        //设置相关设置
        mRecyclerView?.adapter = mAdapter
        mSmartRefreshLayout?.isEnabled = mRefreshEnable
        if (mRefreshEnable) {
            mSmartRefreshLayout?.setOnRefreshListener {
                mLoadPageNum = 1
                mPageNum = 1
                loadPageListData(mPageNum)
            }
        }
        if (mLoadMoreEnable) {
            mSmartRefreshLayout?.setOnLoadMoreListener {
                mLoadPageNum = mPageNum + 1
                mPageNum += 1
                loadPageListData(mPageNum)
            }
        }
    }

    /**
     * 自动刷新
     */
    open fun autoRefresh() {
        if (ListUtils.getCount(mAdapter.data) > 0) {
            mSmartRefreshLayout?.autoRefresh()
        } else {
            showLoading()
            loadPageListData(1)
        }
    }

    /**
     * 数据展示
     */
    override fun showListData(datas: List<T>?, pageNum: Int) {
        this.mPageNum = pageNum
        if (mRefreshEnable) {
            mSmartRefreshLayout?.isEnabled = true
        }
        if (pageNum == 1) {
            mSmartRefreshLayout?.finishRefresh()
            mAdapter.setNewData(datas as MutableList<T>)
        } else {
            mSmartRefreshLayout?.finishLoadMore()
            datas?.let {
                mAdapter.addData(it)
            }
        }
    }

    /**
     * 加载更多--没有更多了
     */
    open fun showNoMore() {
        mSmartRefreshLayout?.finishLoadMoreWithNoMoreData()
    }

    /**
     * 加载更多--当前page发生网络错误
     */
    open fun showLoadMoreError() {
        mSmartRefreshLayout?.finishLoadMore(false)
    }

    /**
     * 刷新完成
     */
    override fun refreshComplete() {
        mSmartRefreshLayout?.finishLoadMore()
        mSmartRefreshLayout?.finishRefresh()
    }

}