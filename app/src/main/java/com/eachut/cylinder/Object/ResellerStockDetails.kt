package com.eachut.cylinder.Object

import com.eachut.cylinder.entity.ResellerStock

object ResellerStockDetails {
    var data = ResellerStock()
    var flag = false
    fun setResellerStockDetails(resellerStock: ResellerStock){
        data = resellerStock
        flag = true
    }
    fun getResellerStockDetails():ResellerStock{
        return data
    }
    fun isData():Boolean
    {
        return flag
    }

}