package com.hmhco.quality.mi.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.hmhco.quality.mi.config.Config

object CreateAndStartAssessment {

  var testId = ""


//Hitting the CreateStartAssesment API and generating the test. Storing the test id in the var=testId

  val createStartAssessment = exec(
    http("Creating Assessment").post(Config.httpUrl+"/users/${refId}/actions")
      .header("Accept","*/*")
      .header("Authorization","${token}")
      .header("ActivityId","${refId}")
      .check(status.is(201))
      .check(jsonPath("$.testId").saveAs("testId"))
  ).exec(session => {
    testId = session("testId").as[String].trim
    session}
  )
}
