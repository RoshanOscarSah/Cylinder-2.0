package com.eachut.cylinder.response

import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.entity.ResellerStock

data class GetAllResellerStockResponse (
    val success:Boolean?=null,
    val message:String?=null,
    val data: MutableList<ResellerStock>?=null
        )