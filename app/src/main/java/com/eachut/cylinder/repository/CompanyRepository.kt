package com.eachut.cylinder.repository

import com.eachut.cylinder.api.CompanyAPI
import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.response.AddNewResellerResponse

class CompanyRepository: MyApiRequest() {
    private val companyAPI =
        ServiceBuilder.buildService(CompanyAPI::class.java)

//    //    Add New Company
//    suspend fun newCompany(company: Company): AddNewCompanyResponse {
//        return apiRequest {
//            companyAPI.newCompany(company)
//        }
//    }
}