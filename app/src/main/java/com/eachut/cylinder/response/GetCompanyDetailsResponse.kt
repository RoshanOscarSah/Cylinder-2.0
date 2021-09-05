package com.eachut.cylinder.response

data class GetCompanyDetailsResponse (
    val success:Boolean?=null,
    val Amount:String?=null,
    val CylinderLended: String?=null,
    val LeakCylinderGiven: String?=null,
    val Rate: String?=null,
    val GasSold: String?=null,
    val CylinderSold: String?=null,
)