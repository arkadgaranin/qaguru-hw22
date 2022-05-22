package com.gmail.arkgaranin.helpers;

import com.gmail.arkgaranin.config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class Browserstack {

  public static String videoUrl(String sessionId) {
    CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    String login = config.browserStackUser();
    String password = config.browserStackKey();
    return given()
        .auth().basic(login, password)
        .when()
        .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId + ".json")
        .then()
        .statusCode(200)
        .log().body()
        .extract()
        .response()
        .path("automation_session.video_url");
  }
}
