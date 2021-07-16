package com.eachut.cylinder.api
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.response.AddNewCompanyResponse
import retrofit2.Response
import retrofit2.http.*

interface CompanyAPI {
    //Add Company
    @POST("/newCompany")
    suspend fun newCompany(
        @Body company: Company
    ):Response<AddNewCompanyResponse>
}