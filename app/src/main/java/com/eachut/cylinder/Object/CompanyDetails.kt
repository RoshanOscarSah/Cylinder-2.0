package com.eachut.cylinder.Object

import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.Reseller

object CompanyDetails {
    var data = Company()
    fun setCompany(company: Company){
        data=company
    }
    fun getCompany(): Company {
        return data
    }
}