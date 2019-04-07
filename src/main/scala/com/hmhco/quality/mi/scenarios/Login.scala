package com.hmhco.quality.mi.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.hmhco.quality.mi.config.Config
import com.hmhco.quality.mi.util.ScenarioTrait
import com.hmhco.quality.mi.util.MIUtils

object Login extends ScenarioTrait with MIUtils {

  var token = ""

  def LoginFlow() = exec {
    login
  }

//Student will login with username and password into Ed system.
// Generated token will be decoded and stored into the var=token

  def login() = exec { session =>
    session.set("authorize_url",getBaseUrl()+"/authorize")
      .set("redirect_uri", getRedirectURI(Config.TARGET_ENV))
      .set("client_id", Config.getProperty("client_id", "152ced50-1369-4b19-8b26-8f3d5d9bfd6a.hmhco.com"))
  }.doIfOrElse(session => session.contains("token")) {
    exec { session =>
      session
    }
  } {
    exec { session =>
      val username = session("username").as[String]
      val password = session("password").as[String]
      session.set("nonce", "99999")
    }.exec(
      http("Authorize - /authorize")
        .post("${authorize_url}")
        .queryParam("client_id", "${client_id}")
        .queryParam("scope", "openid")
        .queryParam("response_type", "id_token token")
        .queryParam("nonce", "${nonce}")
        .queryParam("state", "${redirect_uri}")
        .queryParam("redirect_uri", "${redirect_uri}")
        .queryParam("connection", "${connection}")
        .formParam("username", "${username}")
        .formParam("password", "${password}")
        .disableFollowRedirect
        .check(
          status.is(302),
          headerRegex("Location", "${redirect_uri}#access_token=(.*?)&")
            .find.transform(string => java.net.URLDecoder.decode(string, "UTF-8")).saveAs("token")
        )
    ).exec(session => {
      token = session("token").as[String].trim
      session
    }
    )
  }

}
