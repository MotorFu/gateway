package com.motorfu.gateway.core.init;

import com.motorfu.gateway.core.config.MyGatewayProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;


@Order(0)
@Component
public class DbScriptInit implements CommandLineRunner {
  private final Logger log= LoggerFactory.getLogger(this.getClass());
  private final HikariDataSource dataSource;
  private final MyGatewayProperties myGatewayProperties;

  @Autowired
  public DbScriptInit(HikariDataSource dataSource, MyGatewayProperties myGatewayProperties) {
    this.dataSource = dataSource;
    this.myGatewayProperties = myGatewayProperties;
  }


  @Override
  public void run(String... args) {
    try {
      log.info("DbScriptInit exec script start version={}", myGatewayProperties.getVersion());
      executeProcedureScript("database/procedure.sql");

      executeScript("database/schema.sql");
      //截取版本号，x.y.z

      executeScript("database/" + myGatewayProperties.getVersion().trim() + "/update.sql");

      log.info("DbConfig exec script end");
    } catch (Exception e) {
      log.error("初始化数据库脚本出现异常", e);
    }
  }

  /**
   * 执行存储过程脚本
   *
   * @param filePath
   */
  public void executeProcedureScript(String filePath) {
    ClassPathResource resource = new ClassPathResource(filePath);
    log.info("resource.exists()-->{}", resource.exists());
    if (resource.exists()) {
      ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
      populator.setSeparator("$$");
      populator.setScripts(resource);
      DatabasePopulatorUtils.execute(populator, dataSource);
    }

  }

  /**
   * 执行更新数据库脚本
   *
   * @param filePath
   */
  public void executeScript(String filePath) {
    ClassPathResource resource = new ClassPathResource(filePath);
    log.info("resource.exists()-->{}", resource.exists());
    if (resource.exists()) {
      ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
      populator.setScripts(resource);
      DatabasePopulatorUtils.execute(populator, dataSource);
    }
  }

  private String handleVersion(String version){
    String v1 = version.split("-")[0];
    String[] v2 = v1.split("\\.");
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < v2.length; i++) {
      if (i < 3) {
        builder.append(v2[i]).append(".");
      }
    }
    return builder.substring(0, builder.toString().length() - 1);
  }

}
