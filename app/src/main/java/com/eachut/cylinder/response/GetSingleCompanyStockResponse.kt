package com.eachut.cylinder.response

import com.eachut.cylinder.entity.CompanyStock
import com.eachut.cylinder.entity.ResellerStock

data class GetSingleCompanyStockResponse (
    val success:Boolean?=null,
    val message:String?=null,
    val data: CompanyStock?=null,
)