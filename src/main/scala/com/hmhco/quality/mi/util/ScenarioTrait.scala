package com.hmhco.quality.mi.util

import com.hmhco.quality.mi.config.Config

trait ScenarioTrait {

  val ENV_DEV = "dev"
  val ENV_INT = "int"
  val ENV_CERT = "cert"
  val ENV_PROD = "prod"


  def getBaseUrl(): String = {
    if (!Config.BASE_URL.isEmpty) {
      Config.BASE_URL
    } else {
      if (Config.COMMON_BASE_URL) {
        getCommonRelativeURL(Config.TARGET_ENV)
      } else {
        getRelativeURL(Config.TARGET_ENV)
      }
    }
  }

  def getRelativeURL(env: String): String

  def getCommonRelativeURL(env: String): String

}
