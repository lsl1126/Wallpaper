package com.hd.wallpaper.network

import com.hd.wallpaper.bean.Data
import retrofit2.http.*

interface ApiService {
    @Headers("Authorization:563492ad6f91700001000001e3d985df44954e759762ca7caf7379d0")
    @GET("v1/search/?per_page=20&query=cat&locale=zh-CN&orientation=portrait&size=large")
    suspend fun getImageList(@Query("page") page: Int): Data;
}