package com.library.common.view

import android.view.View

/**
 * 界面效果实现
 *
 * @author yangbw
 * @date 2020/8/31
 */
interface IVaryViewHelperController {
    /**
     * 显示界面loading View
     */
    fun showLoading()

    /**
     * 显示界面loading
     *
     * @param msg loading文字描述
     */
    fun showLoading(msg: String?)

    /**
     * 显示empty View
     *
     * @param listener 点击事件
     */
    fun showEmpty(listener: View.OnClickListener?)

    /**
     * 显示empty View
     *
     * @param emptyMsg empty View 文字描述
     * @param listener empty View 点击重试按钮
     */
    fun showEmpty(
        emptyMsg: String?,
        listener: View.OnClickListener?
    )

    /**
     * 显示netWorkError View
     *
     * @param listener netWorkError View 点击重试按钮
     */
    fun showError(listener: View.OnClickListener?)

    /**
     * 显示netWorkError View
     *
     * @param msg      netWorkError View 文字描述
     * @param listener netWorkError View 点击重试按钮
     */
    fun showError(
        msg: String?,
        listener: View.OnClickListener?
    )

    /**
     * 显示简单的自定义view
     * @param drawableInt 图标
     * @param title       图标下面一行的文字
     * @param msg         图标下面2行的文字信息
     * @param btnText     按钮文字信息
     * @param listener    按钮点击事件
     */
    fun showCustomView(
        drawableInt: Int,
        title: String?,
        msg: String?,
        btnText: String?,
        listener: View.OnClickListener?
    )

    /**
     * 恢复显示原本View  即每个Activity中实现的getReplaceView
     */
    fun restore()

    /**
     * 是否已经调用过restore()方法
     */
    val isHasRestore: Boolean
}