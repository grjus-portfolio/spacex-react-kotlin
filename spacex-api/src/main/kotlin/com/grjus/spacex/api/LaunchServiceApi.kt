package com.grjus.spacex.api


import com.grjus.spacex.model.Launch
import com.grjus.spacex.model.Mission
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*


interface LaunchServiceApi {
    @GET("launches")
    fun fetchLaunches():Call<List<Launch>>

    @GET("launches/{flightNumber}")
    fun fetchLaunch(@Path("flightNumber") flightNumber:Int):Call<Launch>


}