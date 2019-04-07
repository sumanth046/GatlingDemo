package com.hmhco.quality.performance.util

import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

/**
  * This object simply provides a `main` method that wraps
  * [[io.gatling.app.Gatling]].main, which
  * allows us to do some configuration and setup before
  * Gatling launches.
  */
object GatlingRunner {

  def main(args: Array[String]) {
    val props = new GatlingPropertiesBuilder
    props.sourcesDirectory("./src/test/scala")
    props.binariesDirectory("./target/test-classes")
    props.resultsDirectory("./target/gatling/results")
    props.simulationClass(args.apply(0))
    Gatling.fromMap(props.build)
  }
}
