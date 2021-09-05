package com.grjus.spacex.resolver

import com.grjus.spacex.model.Mission
import com.jayway.jsonpath.TypeRef
import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.PostConstruct


@SpringBootTest(classes = [DgsAutoConfiguration::class, MissionResolver::class])
internal class MissionResolverTest {

    init {
        println("INITIALIZING")
    }

    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    lateinit var missionNames: List<String>

    lateinit var singleMission: Mission

    @PostConstruct
    fun initMisionNames() {
        missionNames = dgsQueryExecutor.executeAndExtractJsonPath(
            """
            {
              missions {
                mission_name
              }
            }

        """.trimIndent(), "data.missions[*].mission_name"
        )
    }

    @PostConstruct
    fun initSingleMission() {
        singleMission = dgsQueryExecutor.executeAndExtractJsonPathAsObject(
            """
            {
              mission(missionId:"9D1B7E0"){
                mission_name
                mission_id
                manufacturers
                payload_ids
                website
                description
              }
            }
        """.trimIndent(), "data.mission", object : TypeRef<Mission>() {}
        )
    }


    @Test
    @DisplayName("Check if 'JCSAT' mission is in mission list")
    fun checkIfJSCATMissionExist() {
        assertTrue("JCSAT" in missionNames)
    }


    @Test
    @DisplayName("Check if all misisions are extracted")
    fun checkIfAllMissionsAreAvailable() {
        assertTrue(missionNames.size == 10)
    }


    @Test
    @DisplayName("Assert that correct mission name was returned")
    fun missionName() {
        assertTrue(singleMission.mission_name == "Thaicom")
    }

    @Test
    @DisplayName("Assert that mission has correct manufacturer")
    fun missionManufacturers() {
        print("Mission manufacturers: ${singleMission.manufacturers.joinToString(",")}")
        assertTrue("Orbital ATK" in singleMission.manufacturers)
    }


}