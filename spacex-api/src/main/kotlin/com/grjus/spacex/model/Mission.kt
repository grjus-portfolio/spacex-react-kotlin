package com.grjus.spacex.model

data class Mission(
    val mission_name:String,
    val mission_id:String,
    val manufacturers:List<String>,
    val payload_ids:List<String>,
    val website:String,
    val description:String,
)
