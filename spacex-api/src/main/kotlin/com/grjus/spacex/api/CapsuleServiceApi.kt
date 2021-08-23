package com.grjus.spacex.api

import com.grjus.spacex.model.Capsule
import retrofit2.Call
import retrofit2.http.GET

interface CapsuleServiceApi {

    @GET("capsules")
    fun fetchCapsules(): Call<List<Capsule>>
}