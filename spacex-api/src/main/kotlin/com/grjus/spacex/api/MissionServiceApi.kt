package com.grjus.spacex.api

import com.grjus.spacex.model.Mission
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MissionServiceApi {

    @GET("missions")
    fun fetchMissions(): Call<List<Mission>>

    @GET("missions/{missionId}")
    fun fetchMission(@Path("missionId") missionId: String): Call<Mission>
}