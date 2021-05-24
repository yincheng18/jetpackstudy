package com.demo.mvvmstudy.ui.fragment.home

import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.demo.mvvmstudy.bean.MeiZiData
import com.demo.mvvmstudy.R
import com.demo.mvvmstudy.databinding.LayoutStickArticleBinding
import com.library.common.base.BaseAdapter
import com.library.common.utils.GlideUtils
import com.library.common.view.baseviewholder.CommonViewHolder
import java.util.ArrayList

/**
 * @author yin
 * @desc
 * @time 2021/4/21
 */
class MeiZiAdapter : BaseAdapter<MeiZiData>(R.layout.layout_stick_article, ArrayList()) {
    override fun onItemViewHolderCreated(viewHolder: CommonViewHolder, viewType: Int) {
        DataBindingUtil.bind<LayoutStickArticleBinding>(viewHolder.itemView)
    }

    override fun convert(helper: CommonViewHolder, item: MeiZiData) {
        val itemListBinding = helper.getBinding<LayoutStickArticleBinding>()
        if (itemListBinding != null) {
            GlideUtils.loadCustomRoundImage(imageView = itemListBinding.ivImage, url = item.url)
        }
    }
}