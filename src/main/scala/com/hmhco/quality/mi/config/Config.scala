package com.hmhco.quality.mi.config

object Config {

  val httpUrl="https://math-inventory-api.staging0.hmheng-math-inventory.br.internal"

  val HEADERS_JSON = Map("Content-Type" -> "application/json", "Accept" -> "application/json", "Charset" -> "UTF-8")

  val BASE_URL = System.getProperty("base_url","")
  val COMMON_BASE_URL = java.lang.Boolean.getBoolean("common_base_url")

  val NUM_USERS = Integer.getInteger("users", 6).toInt

  val iterations = Integer.getInteger("iter", 2).toInt

  val TARGET_ENV = System.getProperty("env","int")

  val DEFAULT_RAMP_UP = 20
  val RAMP_UP = Integer.getInteger("rampup", DEFAULT_RAMP_UP).toInt

  val PAUSE_NAVIGATION_SEC = System.getProperty("pauseNavigation", "5").toInt

  def getProperty(key: String, defaultValue: String):String = {
    System.getProperty(key,defaultValue)
  }

}

