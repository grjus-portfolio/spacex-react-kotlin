package com.grjus.spacex.resolver

import com.grjus.spacex.api.ApiCaller
import com.grjus.spacex.model.Capsule
import com.grjus.spacex.model.Mission
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
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

    suspend fun fetchMatchedCapsules(missionName:String):List<Capsule>{
        val capsules = CapsuleResolver().fetchCapsules()
        return capsules.filter {it.missions.any { inner->inner.name!=missionName }}
    }

    @DgsData(parentType = "Query", field = "missions")
    fun resolveMissions() = runBlocking {
        fetchMissions()
    }

    @DgsData(parentType = "Query", field = "mission")
    fun resolveMission(@InputArgument("missionId") missionId: String) = runBlocking {
        fetchMission(missionId)
    }

    @DgsData(parentType = "Mission", field="capsules")
    fun resolveCapsules(dfe:DgsDataFetchingEnvironment)= runBlocking {
        val mission = dfe.getSource<Mission>()
        fetchMatchedCapsules(mission.mission_name)
    }
}


