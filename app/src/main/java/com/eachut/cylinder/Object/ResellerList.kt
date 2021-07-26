package com.eachut.cylinder.Object

import com.eachut.cylinder.entity.Reseller

object ResellerList {
    var shortedResellerList = mutableListOf<Reseller>()

    fun setResellerList(reseller : MutableList<Reseller>){
        shortedResellerList = reseller
    }
    fun getResellerList():MutableList<Reseller>{
        return shortedResellerList
    }
}
