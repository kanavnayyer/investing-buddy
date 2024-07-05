package com.awesome.api



import com.awesome.investingbuddy.util.AllApi.Companion.Api_key
import com.awesome.model.newsmodel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface newsApi {
    @GET("v2/top-headlines")
    fun getHeadLines(
        @Query("country") countryCode: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = Api_key
    ): Call<newsmodel>
}
