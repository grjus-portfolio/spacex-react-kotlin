package com.grjus.spacex.resolver

import com.grjus.spacex.api.ApiCaller
import com.grjus.spacex.model.Mission
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import kotlinx.coroutines.runBlocking
import retrofit2.await

@DgsComponent
class MissionResolver {

    suspend fun fetchMission(missionId: String): Mission {
        return ApiCaller().missionApi.fetchMission(missionId).await()

    }

    suspend fun fetchMissions(): List<Mission> {
        return ApiCaller().missionApi.fetchMissions().await()

    }

    @DgsData(parentType = "Query", field = "missions")
    fun resolveMissions() = runBlocking {
        fetchMissions()
    }

    @DgsData(parentType = "Query", field = "mission")
    fun resolveMission(@InputArgument("missionId") missionId: String) = runBlocking {
        fetchMission(missionId)
    }
}


