package com.eachut.cylinder.Object

import com.eachut.cylinder.entity.Reseller

object ResellerDetails {
    var data = Reseller()
    fun setReseller(reseller:Reseller){
        data=reseller
    }
    fun getReseller():Reseller{
        return data
    }
}