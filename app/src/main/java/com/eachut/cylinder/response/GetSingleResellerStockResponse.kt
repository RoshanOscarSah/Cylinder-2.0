package com.eachut.cylinder.response

import com.eachut.cylinder.entity.ResellerStock

data class GetSingleResellerStockResponse (
    val success:Boolean?=null,
    val message:String?=null,
    val data: ResellerStock?=null,
)