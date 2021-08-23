package com.eachut.cylinder.response

import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.CompanyStock

data class CompanyStockResponse (
    val success : Boolean?=null,
    val message : String?=null,
    val info : CompanyStock ? =null,
    val comapanyDetails : Company ? =null,
)