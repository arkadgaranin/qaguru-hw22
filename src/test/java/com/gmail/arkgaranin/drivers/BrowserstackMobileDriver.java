package com.gmail.arkgaranin.drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.gmail.arkgaranin.helpers.Browserstack.*;

public class BrowserstackMobileDriver implements WebDriverProvider {

  public static URL getBrowserstackUrl() {
    try {
      return new URL(browserstackUrl);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public WebDriver createDriver(Capabilities capabilities) {
    MutableCapabilities mutableCapabilities = new MutableCapabilities();
    mutableCapabilities.merge(capabilities);
    mutableCapabilities.setCapability("browserstack.appium_version", "1.22.0");
    mutableCapabilities.setCapability("browserstack.user", browserstackLogin);
    mutableCapabilities.setCapability("browserstack.key", browserstackPassword);
    mutableCapabilities.setCapability("app", appUrl);
    mutableCapabilities.setCapability("device", "Google Pixel 3");
    mutableCapabilities.setCapability("os_version", "9.0");
    mutableCapabilities.setCapability("project", "First Java Project");
    mutableCapabilities.setCapability("build", "browserstack-build-1");
    mutableCapabilities.setCapability("name", "first_test");

    return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
  }
}
