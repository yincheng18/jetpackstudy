package com.library.common.utils

import android.content.Context
import android.util.TypedValue

/**
 * @author yin
 * @desc
 * @time 2021/5/24
 */

/**
 * 常用单位转换的工具类
 */
object DensityUtils {
    /**
     * dp转px
     *
     * @param context
     * @return
     */
    fun dp2px(context: Context, dpVal: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dpVal, context.resources
                .displayMetrics
        )
    }

    /**
     * sp转px
     *
     * @param context
     * @return
     */
    fun sp2px(context: Context, spVal: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, spVal, context.resources
                .displayMetrics
        )
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    fun px2dp(context: Context, pxVal: Float): Float {
        val scale: Float = context.resources.displayMetrics.density
        return pxVal / scale
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    fun px2sp(context: Context, pxVal: Float): Float {
        return pxVal / context.resources.displayMetrics.scaledDensity
    }

    /**
     * 得到屏幕宽度
     *
     * @param context
     * @return
     */
    fun getDisplayWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    /**
     * 得到屏幕高度
     *
     * @param context
     * @return
     */
    fun getDisplayHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }
}
