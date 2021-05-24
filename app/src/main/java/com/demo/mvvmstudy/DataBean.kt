package com.demo.mvvmstudy

import androidx.annotation.Keep
import java.io.Serializable

/**
 * @author yin
 * @desc
 * @time 2021/4/21
 */
@Keep
data class MeiZi(
    val `data`: List<MeiZiData>,
    val page: Int,
    val page_count: Int,
    val status: Int,
    val total_counts: Int
)

@Keep
data class MeiZiData(
    val _id: String,
    val author: String,
    val category: String,
    val createdAt: String,
    val desc: String,
    val images: List<String>,
    val likeCounts: Int,
    val publishedAt: String,
    val stars: Int,
    val title: String,
    val type: String,
    val url: String,
    val views: Int
) : Serializable