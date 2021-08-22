package com.grjus.spacex.api


import com.grjus.spacex.model.Launch
import com.grjus.spacex.model.Mission
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface SpaceXApi {
    @GET("launches")
    fun fetchLaunches():Call<List<Launch>>

    @GET("missions/{missionId}")
    fun fetchMissions(@Path("missionId") missionId:String?):Call<List<Mission>>
}