package com.grjus.spacex.model

data class Launch(
    val flight_number:Int,
    val mission_name:String,
    val launch_year:String,
    val details:String,
    val rocket:Rocket

)
