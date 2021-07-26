package com.eachut.cylinder.response

import com.eachut.cylinder.entity.ResellerStock

data class ResellerStockResponse (
    val success : Boolean?=null,
    val message : String?=null,
    val info : ResellerStock?=null,
        )