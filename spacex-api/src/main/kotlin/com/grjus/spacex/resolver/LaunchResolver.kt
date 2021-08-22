package com.grjus.spacex.resolver

import com.grjus.spacex.api.ApiCaller
import com.grjus.spacex.model.Launch
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import kotlinx.coroutines.runBlocking
import retrofit2.await

@DgsComponent
class LaunchResolver {

    suspend fun fetchData(missionName: String?):List<Launch>{

        val fetchResults = ApiCaller().api.fetchLaunches().await()
        if (missionName==null){
            return fetchResults
        }
        return fetchResults.filter { it.mission_name.lowercase().contains(missionName.lowercase()) }
    }

    @DgsQuery
    fun launches(@InputArgument missionName:String?) = runBlocking {
        fetchData(missionName)
    }



}