package com.gmail.arkgaranin.tests;

import com.codeborne.selenide.Configuration;
import com.gmail.arkgaranin.config.MobileConfig;
import com.gmail.arkgaranin.drivers.BrowserstackMobileDriver;
import com.gmail.arkgaranin.drivers.LocalMobileDriver;
import com.gmail.arkgaranin.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static com.gmail.arkgaranin.helpers.Attach.getSessionId;

public class TestBase {

  static MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

  @BeforeAll
  public static void setup() {
    addListener("AllureSelenide", new AllureSelenide());

    switch (config.device()) {
      case "local":
      case "emulator":
        Configuration.browser = LocalMobileDriver.class.getName();
        break;
      case "remote":
        Configuration.browser = BrowserstackMobileDriver.class.getName();
        break;
    }

    Configuration.browserSize = null;
  }

  @BeforeEach
  public void startDriver() {
    open();

    Attach.attachAsText("Режим запуска: ", config.device());
    Attach.attachAsText("Устройство:", config.deviceName());
    Attach.attachAsText("Версия приложения:", config.appUrl());
    Attach.attachAsText("Browserstack URL: ", config.browserstackUrl());
  }

  @AfterEach
  public void afterEach() {
    String sessionId = getSessionId();

    Attach.screenshotAs("Last screenshot");
    Attach.pageSource();

    closeWebDriver();

    if (config.device().contains("remote")) {
      Attach.video(sessionId);
    }
  }
}
