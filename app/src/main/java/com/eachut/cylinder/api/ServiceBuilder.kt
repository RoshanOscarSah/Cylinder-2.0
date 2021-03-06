package com.eachut.cylinder.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val BASE_URL = "http://10.0.2.2:90/"
//    private const val BASE_URL = "http://172.26.0.36:90/"  //laptop IP address
//    private const val BASE_URL = "http://localhost:90/"

    private val okHttp = OkHttpClient.Builder()
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    //Create retrofit instance
    private val retrofit = retrofitBuilder.build()

    //Generic function

    fun<T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}