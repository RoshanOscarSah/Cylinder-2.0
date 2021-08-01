package com.eachut.cylinder.Object

import com.eachut.cylinder.entity.ResellerStock

object ResellerStockDetails {
    var data = ResellerStock()
    fun setResellerStockDetails(resellerStock: ResellerStock){
        data = resellerStock
    }
    fun getResellerStockDetails():ResellerStock{
        return data
    }
}