package com.eachut.cylinder.repository

import com.eachut.cylinder.api.CompanyAPI
import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.response.*

class CompanyRepository: MyApiRequest() {
    private val companyAPI =
        ServiceBuilder.buildService(CompanyAPI::class.java)

    //    Add New Company
    suspend fun newCompany(company: Company): AddNewCompanyResponse {
        return apiRequest {
            companyAPI.newCompany(company)
        }
    }

    //get Reseller
    suspend fun allCompanyList(): GetAllCompanyResponse {
        return apiRequest {
            companyAPI.allCompanyList()
        }
    }

    //get single reseller
    suspend fun companyList(id:String): GetCompanyResponse {
        return apiRequest {
            companyAPI.companyList(id)
        }
    }

    //update reseller
    suspend fun updateReseller(id:String, company: Company):AddNewCompanyResponse{
        return apiRequest {
            companyAPI.updateReseller(id, company)
        }
    }

}