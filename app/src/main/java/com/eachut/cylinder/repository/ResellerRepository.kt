package com.eachut.cylinder.repository

import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ResellerAPI
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.response.AddNewResellerResponse

class ResellerRepository : MyApiRequest() {
    private val resellerAPI =
        ServiceBuilder.buildService(ResellerAPI::class.java)

//    Add New Member Admin
    suspend fun addNewReseller(reseller: Reseller): AddNewResellerResponse {
        return apiRequest {
            resellerAPI.addNewReseller(reseller)
        }
    }
}