package com.grjus.spacex.model

data class RocketDetails(
    val id: Int,
    val active: Boolean,
    val stages: Int,
    val cost_per_launch: Int,
    val first_flight: String,
    val country: String,
    val flickr_images: List<String>,
    val description: String
)
