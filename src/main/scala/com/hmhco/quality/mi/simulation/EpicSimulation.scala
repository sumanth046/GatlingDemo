package com.hmhco.quality.mi.simulation

import io.gatling.core.Predef._
import io.gatling.core.Predef.Simulation

import scala.concurrent.duration._
import com.hmhco.quality.mi.config.Config
import com.hmhco.quality.mi.scenarios.{CreateAndStartAssessment, GetStudentProfile, Login, PostAnswerAndNextQuestion,MathInventoryAPI}
import com.hmhco.quality.mi.util.{ScenarioTrait, SimulationTrait}


class EpicSimulation extends  Simulation with SimulationTrait {

 val prefix = s"users_${Config.TARGET_ENV}"

  val userFeeder = createFeeder(prefix)

  val epicScenario = scenario("Epic 729 API scenario").feed(userFeeder)
    .repeat(Config.iterations) {
        exec(Login.LoginFlow())
        .exec(MathInventoryAPI.Actionflow)
    }

  var testSetup = setUp(
    epicScenario.inject(
      heavisideUsers(Config.NUM_USERS) over(Config.RAMP_UP second)
    )
  )

}
