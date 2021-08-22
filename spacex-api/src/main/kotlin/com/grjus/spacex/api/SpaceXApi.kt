package com.grjus.spacex.api


import com.grjus.spacex.model.Launch
import retrofit2.Call
import retrofit2.http.GET


interface SpaceXApi {
    @GET("launches")
    fun fetchLaunches():Call<List<Launch>>

    @GET("missions")
    fun fetchMissions():Call<List<Launch>>
}