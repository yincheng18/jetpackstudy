package com.demo.mvvmstudy

import android.content.Context
import com.library.common.base.BaseApplication
import com.library.common.config.AppConfig
import com.library.common.http.ApiClient
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.demo.mvvmstudy.interceptor.RequestHeaderInterceptor
import com.demo.mvvmstudy.interceptor.SessionInterceptor
import okhttp3.logging.HttpLoggingInterceptor

/**
 * APP
 *
 * @author yangbw
 * @date 2020/9/9
 */
class App : BaseApplication() {

    companion object {
        lateinit var context: Context
    }

    init {

        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
            MaterialHeader(context).setColorSchemeResources(R.color.main_color)
        }

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context, layout: RefreshLayout ->
            layout.setEnableFooterFollowWhenNoMoreData(true)
            return@setDefaultRefreshFooterCreator ClassicsFooter(context).setDrawableSize(20f)
        }
    }

    override fun initConfig() {
        context = this
        AppConfig.builder()
            //单地址和多地址设置
            .setBaseUrl(Constant.BASE_URL)
            .setRetSuccess(Constant.CODE_SUCCESS)
            //日志开关
            .setLogOpen(Constant.OPEN_LOG)
            //请求头拦截器
            .addOkHttpInterceptor(RequestHeaderInterceptor())
            //请求日志开关
            .addOkHttpInterceptor(
                Constant.OPEN_LOG,
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            // returnCode非正常态拦截器
            .addRetCodeInterceptors(SessionInterceptor())
            .setRetrofit(
                ApiClient.getInstance().getRetrofit(
                    ApiClient.getInstance().getOkHttpClient(
                        AppConfig.getOkHttpInterceptors()
                    )
                )
            )
            .build()
    }
}