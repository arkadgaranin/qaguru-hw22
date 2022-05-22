package com.gmail.arkgaranin.drivers;

import com.codeborne.selenide.WebDriverProvider;
import com.gmail.arkgaranin.config.CredentialsConfig;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

  public static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

  String user = config.browserStackUser();
  String key = config.browserStackKey();
  String url = config.browserStackAppURL();

  public static URL getBrowserstackUrl() {
    try {
      return new URL("http://hub.browserstack.com/wd/hub");
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public WebDriver createDriver(Capabilities capabilities) {
    MutableCapabilities mutableCapabilities = new MutableCapabilities();
    mutableCapabilities.merge(capabilities);
    mutableCapabilities.setCapability("browserstack.user", user);
    mutableCapabilities.setCapability("browserstack.key", key);
    mutableCapabilities.setCapability("app", url);
    mutableCapabilities.setCapability("device", "Google Pixel 3");
    mutableCapabilities.setCapability("os_version", "9.0");
    mutableCapabilities.setCapability("project", "First Java Project");
    mutableCapabilities.setCapability("build", "browserstack-build-1");
    mutableCapabilities.setCapability("name", "first_test");

    return new AndroidDriver(getBrowserstackUrl(), mutableCapabilities);
  }
}
