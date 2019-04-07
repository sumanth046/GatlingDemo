package com.hmhco.quality.mi.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.hmhco.quality.mi.config.Config
import com.hmhco.quality.mi.util.MIUtils
import com.hmhco.quality.mi.util.ScenarioTrait

object GetStudentProfile extends MIUtils  {

  var refId = ""

  //Hitting the GetStudentProfile API and getting the student refernce Id, Storing refernce Id in the var=refId

  val getStudentProfile = exec(
    http("Getting Student Profile").get(Config.httpUrl + "/student")
      .header("Accept", "*/*")
      .header("Authorization", "${token}")
      .check(status.is(200))
      .check(jsonPath("$.refId").saveAs("refId"))
  ).exec(session => {
    refId = session("refId").as[String].trim
    session
  }
  )

}