package com.eachut.cylinder.repository

import com.eachut.cylinder.api.MyApiRequest
import com.eachut.cylinder.api.ResellerAPI
import com.eachut.cylinder.api.ServiceBuilder
import com.eachut.cylinder.entity.Reseller
import com.eachut.cylinder.response.AddNewResellerResponse
import com.eachut.cylinder.response.GetAllResellerResponse
import com.eachut.cylinder.response.GetResellerResponse
import com.eachut.cylinder.response.GettotalResellerResponse

class ResellerRepository : MyApiRequest() {
    private val resellerAPI =
        ServiceBuilder.buildService(ResellerAPI::class.java)

//    Add New Member Admin
    suspend fun addNewReseller(reseller: Reseller): AddNewResellerResponse {
        return apiRequest {
            resellerAPI.addNewReseller(reseller)
        }
    }

    //get Reseller
        suspend fun allresellerList():GetAllResellerResponse{
        return apiRequest {
            resellerAPI.allresellerList()
        }
    }

    //get single reseller
    suspend fun resellerList(id:String):GetResellerResponse{
        return apiRequest {
            resellerAPI.resellerList(id)
        }
    }

    //update reseller
    suspend fun updateReseller(id:String, reseller: Reseller):AddNewResellerResponse{
        return apiRequest {
            resellerAPI.updateReseller(id, reseller)
        }
    }

    //get Total Reseller
    suspend fun totalreseller():GettotalResellerResponse{
        return apiRequest {
            resellerAPI.totalreseller()
        }
    }

}