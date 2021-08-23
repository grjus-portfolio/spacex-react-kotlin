package com.grjus.spacex.api

import retrofit2.GsonConverterFactory
import retrofit2.Retrofit

class ApiCaller {
    private val BASE_URL = "https://api.spacexdata.com/v3/"

    val missionApi: MissionServiceApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MissionServiceApi::class.java)

    val lauchApi: LaunchServiceApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory((GsonConverterFactory.create()))
        .build()
        .create(LaunchServiceApi::class.java)

    val rocketApi:RocketServiceApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory((GsonConverterFactory.create()))
        .build()
        .create(RocketServiceApi::class.java)
}