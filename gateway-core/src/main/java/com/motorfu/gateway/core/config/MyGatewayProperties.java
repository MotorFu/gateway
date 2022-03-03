package com.motorfu.gateway.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author fufengming
 * @email ffu@leapcloud.cn
 * @since 2020/11/5 17:32
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "gateway")
public class MyGatewayProperties {
  private String version;


  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}
