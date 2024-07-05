package com.awesome.api



import com.awesome.investingbuddy.util.AllApi.Companion.base_url

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {




    val newsApiService: newsApi by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(newsApi::class.java)
    }
}
