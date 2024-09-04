package com.wan.android.compose.model

import java.io.Serializable

/**
 * @Description:
 * @Date: 2024/9/3 15:43
 * @author:  liuwenjie09
 * @version: 1.0
 */
class ResponseModel<T> :BaseModel(), Serializable {
    var data: T? = null
}