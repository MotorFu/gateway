package com.motorfu.gateway.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author motorfu
 * @email ffu@leapcloud.cn
 * @since 2018/10/17 14:35
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = {
  GatewayCoreAutoConfigure.class
})
public class GatewayCoreAutoConfigure {

}
