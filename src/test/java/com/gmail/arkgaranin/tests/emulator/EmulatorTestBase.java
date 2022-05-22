package com.gmail.arkgaranin.tests.emulator;

import com.codeborne.selenide.Configuration;
import com.gmail.arkgaranin.drivers.LocalMobileDriver;
import com.gmail.arkgaranin.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class EmulatorTestBase {

  @BeforeAll
  public static void setup() {
    addListener("AllureSelenide", new AllureSelenide());

    Configuration.browser = LocalMobileDriver.class.getName();
    Configuration.browserSize = null;
    Configuration.timeout = 10000;
  }

  @BeforeEach
  public void startDriver() {
    open();
  }

  @AfterEach
  public void afterEach() {
    Attach.screenshotAs("Last screenshot");
    Attach.pageSource();

    closeWebDriver();
  }
}
