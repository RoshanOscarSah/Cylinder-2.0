package com.eachut.cylinder.response

import com.eachut.cylinder.entity.Member

data class StockResponse (
    val success : Boolean? = null,
    val message: String?=null,
    var etPrimaF : String? = null,
    var etPrimaH  :String?=null,
    var etPrimaE : String ? =null,
    var etKamakhyaF : String? = null,
    var etKamakhyaH  :String?=null,
    var etKamakhyaE : String ? =null,
    var etSuvidhaF : String? = null,
    var etSuvidhaH  :String?=null,
    var etSuvidhaE : String ? =null,
    var etOthersF : String? = null,
    var etOthersH  :String?=null,
    var etOthersE : String ? =null,
        )