package com.library.common.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.library.common.R
import com.library.common.extension.gone
import com.library.common.extension.visible

/**
 * 弹出浮动加载进度条
 *
 * @author yangbw
 * @date 2020/8/31
 */
class LoadingDialog(private val mContext: Context) {
    /**
     * 加载数据对话框
     */
    private var mLoadingDialog: Dialog? = null

    /**
     * 显示加载对话框
     *
     * @param msg        对话框显示内容
     * @param cancelable 对话框是否可以取消
     */
    fun show(msg: String?, cancelable: Boolean) {
        val view = View.inflate(mContext, R.layout.dialog_loading, null)
        val tvTips = view.findViewById<View>(R.id.tv_tips) as TextView
        if (!TextUtils.isEmpty(msg)) {
            tvTips.text = msg
            tvTips.visible()
        } else {
            tvTips.gone()
        }
        mLoadingDialog = Dialog(mContext, R.style.CustomProgressDialog)
        mLoadingDialog!!.setCancelable(cancelable)
        mLoadingDialog!!.setCanceledOnTouchOutside(false)
        mLoadingDialog!!.setContentView(view)
        if (null != mLoadingDialog
            && !(mContext as Activity).isFinishing
        ) {
            mLoadingDialog!!.show()
        }
    }

    /**
     * 判断是否显示
     *
     * @return
     */
    val isShowing: Boolean
        get() = mLoadingDialog!!.isShowing

    @Suppress("unused")
    fun show() {
        show(null, false)
    }

    /**
     * 关闭对话框
     */
    fun cancel() {
        if (null != mLoadingDialog
            && mLoadingDialog!!.isShowing
        ) {
            mLoadingDialog!!.dismiss()
        }
    }

}