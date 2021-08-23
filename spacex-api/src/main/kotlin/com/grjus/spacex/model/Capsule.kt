package com.grjus.spacex.model

data class Capsule(
    val capsule_serial:String,
    val capsule_id:String,
    val status:String,
    val mission:CapsuleMission,
    val details:String

)

data class CapsuleMission(
    val name:String,
    val flight:Int,
    )
