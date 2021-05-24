package com.demo.mvvmstudy.api

import com.demo.mvvmstudy.bean.MeiZiData
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    /**
     *获取图片
     */
    @GET("api/v2/data/category/Girl/type/Girl/page/{page}/count/{count}")
    suspend fun getMeiZi(
        @Path("page") page: Int,
        @Path("count") count: Int
    ): BaseRes<List<MeiZiData>>

}