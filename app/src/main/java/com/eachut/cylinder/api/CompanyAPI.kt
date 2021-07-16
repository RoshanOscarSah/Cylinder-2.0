package com.eachut.cylinder.api
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.response.*
import retrofit2.Response
import retrofit2.http.*

interface CompanyAPI {
    //Add Company
    @POST("/newCompany")
    suspend fun newCompany(
        @Body company: Company
    ):Response<AddNewCompanyResponse>

    @GET("/companyList")
    suspend fun allCompanyList(
//        @Header("Authorization") token : String,
    ): Response<GetAllCompanyResponse>

    @GET("/companyList/{id}")
    suspend fun companyList(
//        @Header("Authorization") token : String,
        @Path ("id") id: String,
    ): Response<GetCompanyResponse>


    @PUT("/company/update/{id}")
    suspend fun updateReseller(
//        @Header("Authorization") token: String,
        @Path ("id") id: String,
        @Body company: Company
    ): Response<AddNewCompanyResponse>

}