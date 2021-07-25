package com.eachut.cylinder.Object

import com.eachut.cylinder.entity.Company

object CompanyList {
    var shortedCompanyList = mutableListOf<Company>()
    fun setCompanyList(companyList: MutableList<Company>){
        shortedCompanyList = companyList
    }
    fun getCompanyList():MutableList<Company>{
        return shortedCompanyList
    }
}