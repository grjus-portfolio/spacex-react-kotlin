package com.grjus.spacex.resolver

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [DgsAutoConfiguration::class, LaunchResolver::class])
internal class LaunchResolverTest {

    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor


    @Test
    fun launches() {
        val missionNames: List<String> = dgsQueryExecutor.executeAndExtractJsonPath(
            """
                {
                  launches {
                    mission_name
                    launch_year
                  }
                }

            """.trimIndent(), "data.launches[*].mission_name"
        )
        assertTrue("FalconSat" in missionNames)
        assertTrue("DemoSat" in missionNames)
    }

    @Test
    fun singleLaunch() {
        val missionNames: List<String> = dgsQueryExecutor.executeAndExtractJsonPath(
            """
                {
                  launches(missionName:"trail") {
                    mission_name
                    launch_year
                  }
                }
            """.trimIndent(), "data.launches[*].mission_name"
        )
        assertTrue("Trailblazer" in missionNames)
        assertTrue(missionNames.size == 1)
    }

}