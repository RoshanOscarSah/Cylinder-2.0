package com.eachut.cylinder.response

import com.eachut.cylinder.entity.Reseller

data class GettotalResellerResponse (
    val success:Boolean?=null,
    val totalReseller:String? = null,
    val latest:Reseller? = null,
        )