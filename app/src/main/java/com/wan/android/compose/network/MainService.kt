package com.wan.android.compose.network

import com.wan.android.compose.model.ArticleListResp
import com.wan.android.compose.model.MainBannerItem
import com.wan.android.compose.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Description:
 * @Date: 2024/9/3 15:34
 * @author:  liuwenjie09
 * @version: 1.0
 */
interface MainService {

    // 获取首页文章
    @GET("/article/list/{pageIndex}/json")
    suspend fun getArticleList(@Path("pageIndex") pageIndex: Int,@Query("page_size") page_size:Int = 40): ResponseModel<ArticleListResp>

    @GET("/banner/json")
    suspend fun getMainBanner(): ResponseModel<List<MainBannerItem>>

    companion object {
        fun getInstance(): MainService {
            return Retrofit.createService()
        }
    }
}