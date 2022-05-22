package com.gmail.arkgaranin.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:credential.properties"})
public interface CredentialsConfig extends Config {

  String browserStackUser();

  String browserStackKey();

  String browserStackAppURL();

  String selenoidUser();

  String selenoidPass();

  @Key("device.name")
  String deviceName();

  @Key("platform.name")
  String platformName();

  @Key("platform.version")
  String platformVersion();
}
