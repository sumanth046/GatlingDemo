package com.hmhco.quality.mi.util

import com.hmhco.quality.mi.scenarios.Login

trait MIUtils extends ScenarioTrait {

  val defaultUrl = "http://hmh-identity-provider.int.br.hmheng.io"

  val environmentUrls = Map(
    ENV_DEV -> "http://hmh-identity-provider.dev.br.hmheng.io",
    ENV_INT -> defaultUrl,
    ENV_CERT -> "https://hmh-identity-provider.cert.br.hmheng.io",
    ENV_PROD -> "https://hmh-identity-provider.br.hmheng.io"
  )

  val defaultRedirectUri = "http://int.hmhone.app.hmhco.com/arvo/logged-in/"

  val redirectURIs = Map(
    ENV_INT -> defaultRedirectUri,
    ENV_CERT -> "https://cert.hmhco.com/arvo/logged-in/",
    ENV_PROD -> "https://www.hmhco.com/one/logged-in/"
  )

  override def getCommonRelativeURL(env: String): String = {
    environmentUrls.getOrElse(env, defaultUrl)
  }

  override def getRelativeURL(env: String): String = {
    getCommonRelativeURL(env)
  }

  def getRedirectURI(env: String):String = {
    redirectURIs.getOrElse(env, defaultRedirectUri)
  }


}
