package com.library.common.utils

import android.view.View
import com.library.common.utils.ViewClickDelay.SPACE_TIME
import com.library.common.utils.ViewClickDelay.hash
import com.library.common.utils.ViewClickDelay.lastClickTime

/**
 * @author yin
 * @desc
 * @time 2021/4/29
 */

/**
 * @Anthor:Tian
 * @Date:2020/10/5
 * @Description:防止双击
 */
object ViewClickDelay {
    var hash: Int = 0
    var lastClickTime: Long = 0
    var SPACE_TIME: Long = 1000
}

infix fun View.clickDelay(clickAction: () -> Unit) {
    this.setOnClickListener {
        if (this.hashCode() != hash) {
            hash = this.hashCode()
            lastClickTime = System.currentTimeMillis()
            clickAction()
        } else {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime > SPACE_TIME) {
                lastClickTime = System.currentTimeMillis()
                clickAction()
            }
        }
    }
}
