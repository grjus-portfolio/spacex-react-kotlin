package com.grjus.spacex.resolver

import com.grjus.spacex.api.ApiCaller
import com.grjus.spacex.model.Capsule
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import kotlinx.coroutines.runBlocking
import retrofit2.await

@DgsComponent
class CapsuleResolver {

    suspend  fun fetchCapsules():List<Capsule>{
        return ApiCaller().capsuleApi.fetchCapsules().await()
    }

    @DgsData(parentType = "Query", field = "capsules")
    fun capsules() = runBlocking {
        fetchCapsules()
    }

}