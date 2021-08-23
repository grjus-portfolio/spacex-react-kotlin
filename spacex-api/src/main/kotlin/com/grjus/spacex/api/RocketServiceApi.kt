package com.grjus.spacex.api

import com.grjus.spacex.model.RocketDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RocketServiceApi {

    @GET("rockets/{rocketId}")
    fun fetchRocket(@Path("rocketId") rocketId:String):Call<RocketDetails>

}