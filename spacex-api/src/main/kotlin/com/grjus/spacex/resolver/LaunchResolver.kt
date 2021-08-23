package com.grjus.spacex.resolver

import com.grjus.spacex.api.ApiCaller
import com.grjus.spacex.model.Launch
import com.grjus.spacex.model.RocketDetails
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.InputArgument
import kotlinx.coroutines.runBlocking
import retrofit2.await

@DgsComponent
class LaunchResolver {

    suspend fun fetchLaunches(missionName: String?): List<Launch> {
        val fetchResults = ApiCaller().lauchApi.fetchLaunches().await()
        if (missionName == null) {
            return fetchResults
        }
        return fetchResults.filter { it.mission_name.lowercase().contains(missionName.lowercase()) }
    }

    suspend fun fetchLaunch(flightNumber: Int): Launch {
        return ApiCaller().lauchApi.fetchLaunch(flightNumber).await()
    }

    suspend fun fetchRocketDetails(rocketId:String):RocketDetails{
        return ApiCaller().rocketApi.fetchRocket(rocketId).await()

    }

    @DgsData(parentType = "Query", field = "launches")
    fun launches(@InputArgument missionName: String?) = runBlocking {
        fetchLaunches(missionName)
    }

    @DgsData(parentType = "Query", field = "launch")
    fun launch(@InputArgument flightNumber: Int) = runBlocking {
        fetchLaunch(flightNumber)
    }

    @DgsData(parentType = "Launch", field = "rocket_details")
    fun missionDetails(dgs:DgsDataFetchingEnvironment):RocketDetails = runBlocking {
        val launch = dgs.getSource<Launch>()
        fetchRocketDetails(launch.rocket.rocket_id)
    }


}