package com.demo.mvvmstudy.ui.fragment.home

import com.demo.mvvmstudy.api.ApiService
import com.library.common.mvvm.BaseListViewModel

/**
 * @author yin
 * @desc
 * @time 2021/4/21
 */
class HomeViewModel:BaseListViewModel<ApiService>() {
    fun discoverHot(pageNo: Int, size: Int) {
        launchOnlyResult(
            block = {
                getApiService().getMeiZi(pageNo, size)
            },
            reTry = {
                discoverHot(pageNo, size)
            },
            pageNo = pageNo
        )
    }
}