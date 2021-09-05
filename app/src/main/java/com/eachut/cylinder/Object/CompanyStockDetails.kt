package com.eachut.cylinder.Object

import com.eachut.cylinder.entity.CompanyStock

object CompanyStockDetails {
    var data = CompanyStock()
    var flag = false
    fun setCompanyStockDetails(companyStock: CompanyStock){
        data = companyStock
        flag = true
    }
    fun getCompanyStockDetails():CompanyStock{
        return data
    }
    fun isData():Boolean
    {
        return flag
    }

}