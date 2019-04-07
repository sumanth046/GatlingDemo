package com.hmhco.quality.mi.util

import io.gatling.core.Predef._
import io.gatling.core.feeder.{FeederSupport, RecordSeqFeederBuilder}
import io.gatling.http.Predef._
import com.hmhco.quality.mi.config.Config

trait SimulationTrait {

  def createFeeder(filenamePrefix: String): RecordSeqFeederBuilder[String] = {
    csv(filenamePrefix+".csv").records.circular
  }

}
