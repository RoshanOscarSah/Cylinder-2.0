package com.example.cylinderwearos.repository

import com.example.cylinderwearos.api.MyApiRequest
import com.example.cylinderwearos.api.ServiceBuilder
import com.example.cylinderwearos.response.GetbestSellingresponse

class StockRepository: MyApiRequest() {
    private val StockAPI =
        ServiceBuilder.buildService(com.example.cylinderwearos.api.StockAPI::class.java)

    //get best selling
    suspend fun bestSelling(): GetbestSellingresponse {
        return apiRequest {
            StockAPI.bestSelling()
        }
    }
}