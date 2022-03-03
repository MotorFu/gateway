package com.motorfu.gateway.gen;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

/**
 * @author fufengming
 * @email ffu@leapcloud.cn
 * @since 2020/11/5 17:13
 */
public class GatewayStrategy extends DefaultGeneratorStrategy {

  @Override
  public String getJavaClassName(Definition definition, Mode mode) {
    String name = super.getJavaClassName(definition, mode);
    if (mode == Mode.DEFAULT) {
      name += "Table";
    }else if (mode == Mode.POJO) {
      name += "Entity";
    }
    return name;
  }

}
