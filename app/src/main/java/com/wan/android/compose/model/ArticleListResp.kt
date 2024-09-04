package com.wan.android.compose.model

import java.io.Serializable

/**
 * @Description:
 * @Date: 2024/9/3 15:42
 * @author:  liuwenjie09
 * @version: 1.0
 */
class ArticleListResp : Serializable {
    var curPage:Int = 0
    var datas:MutableList<ArticleListItem> = mutableListOf()
}