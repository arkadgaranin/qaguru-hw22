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

import static com.gmail.arkgaranin.drivers.BrowserstackMobileDriver.getBrowserstackUrl;

public class SelenoidMobileDriver implements WebDriverProvider {

  public static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

  public static URL getAppiumServerUrl() {
    try {
      return new URL("https://" + config.selenoidUser() + ":" + config.selenoidPass() +
          "@selenoid.autotests.cloud/wd/hub");
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public WebDriver createDriver(Capabilities capabilities) {
    MutableCapabilities mutableCapabilities = new MutableCapabilities();
    mutableCapabilities.merge(capabilities);
    mutableCapabilities.setCapability("platformName", "Android");
    mutableCapabilities.setCapability("deviceName", "android");
    mutableCapabilities.setCapability("version", "8.1");
    mutableCapabilities.setCapability("locale", "en");
    mutableCapabilities.setCapability("language", "en");
    mutableCapabilities.setCapability("enableVNC", true);
    mutableCapabilities.setCapability("enableVideo", true);
    mutableCapabilities.setCapability("appPackage", "org.wikipedia.alpha");
    mutableCapabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
    mutableCapabilities.setCapability("app", apkUrl());

    return new AndroidDriver(getBrowserstackUrl(), mutableCapabilities);
  }

  private URL apkUrl() {
    try {
      return new URL("https://github.com/wikimedia/apps-android-wikipedia/releases/download/latest/" +
          "app-alpha-universal-release.apk");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
