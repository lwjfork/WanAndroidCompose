package com.wan.android.compose.model

import java.io.Serializable

/**
 * @Description:
 * @Date: 2024/9/3 15:40
 * @author:  liuwenjie09
 * @version: 1.0
 */
open class BaseModel : Serializable {
    var errorCode: Int = -1
    var errorMsg: String = ""
}