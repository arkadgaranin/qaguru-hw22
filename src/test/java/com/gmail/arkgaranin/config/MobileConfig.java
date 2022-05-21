package com.gmail.arkgaranin.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:config/${device}.properties",
    "classpath:config/credentials.properties"})
public interface MobileConfig extends Config {

  @Key("device.name")
  String deviceName();

  @Key("platform.name")
  String platformName();

  @Key("platform.version")
  String platformVersion();

  @Key("browserstackUrl")
  @DefaultValue("http://hub.browserstack.com/wd/hub")
  String browserstackUrl();

  @Key("appUrl")
  @DefaultValue("bs://541891c9268f257a545ba856b69353ad73c74dee")
  String appUrl();

  @Key("device")
  @DefaultValue("emulator")
  String device();

  String browserstackLogin();
  String browserstackPassword();
}
