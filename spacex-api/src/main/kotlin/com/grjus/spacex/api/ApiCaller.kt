package com.grjus.spacex.api

import retrofit2.GsonConverterFactory
import retrofit2.Retrofit

class ApiCaller {
    private val BASE_URL = "https://api.spacexdata.com/v3/"

    val api: SpaceXApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SpaceXApi::class.java)
}