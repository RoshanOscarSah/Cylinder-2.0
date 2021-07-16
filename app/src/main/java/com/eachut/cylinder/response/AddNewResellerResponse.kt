package com.eachut.cylinder.response

import com.eachut.cylinder.entity.Reseller


data class AddNewResellerResponse (
    val success : Boolean? = null,
    val message: String?=null,
    val reseller: Reseller ? =null,
)