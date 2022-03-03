package com.motorfu.gateway.common.utils;

import com.motorfu.gateway.common.base.GatewayException;

import java.util.function.Supplier;

/**
 * @author motorfu
 * @email ffu@leapcloud.cn
 * @since 2018/10/29 13:48
 */
public class ExceptionUtils {

  public static final String MODIFY_FAIL = "修改失败";

  public static final String CREATE_FAIL = "添加失败";

  public static final String REMOVE_FAIL = "删除失败";

  private ExceptionUtils() {}

  public static GatewayException show(int code, String message) {
    return new GatewayException(code, message);
  }

  public static GatewayException show(String message) {
    return new GatewayException(message);
  }

  public static GatewayException show(String message, Object data) {
    return new GatewayException(message, data);
  }

  public static GatewayException show(int code, String message, Throwable throwable) {
    return new GatewayException(code, message, throwable);
  }

  public static GatewayException show(String message, Throwable throwable) {
    return new GatewayException(message, throwable);
  }

  public static GatewayException show(Throwable throwable) {
    return new GatewayException(throwable);
  }


  public static GatewayException modifyFail(int code, String message) {
    return show(code, MODIFY_FAIL + "，" + message);
  }

  public static GatewayException modifyFail(String message) {
    return show(MODIFY_FAIL + "，" + message);
  }

  public static GatewayException modifyFail(String message, Object data) {
    return show(MODIFY_FAIL + "，" + message, data);
  }

  public static GatewayException modifyFail(int code, String message, Throwable throwable) {
    return show(code, MODIFY_FAIL + "，" + message, throwable);
  }

  public static GatewayException modifyFail(String message, Throwable throwable) {
    return show(MODIFY_FAIL + "，" + message, throwable);
  }


  public static GatewayException createFail(int code, String message) {
    return show(code, CREATE_FAIL + "，" + message);
  }

  public static GatewayException createFail(String message) {
    return show(CREATE_FAIL + "，" + message);
  }

  public static GatewayException createFail(String message, Object data) {
    return show(CREATE_FAIL + "，" + message, data);
  }

  public static GatewayException createFail(int code, String message, Throwable throwable) {
    return show(code, CREATE_FAIL + "，" + message, throwable);
  }

  public static GatewayException createFail(String message, Throwable throwable) {
    return show(CREATE_FAIL + "，" + message, throwable);
  }


  public static GatewayException removeFail(int code, String message) {
    return show(code, REMOVE_FAIL + "，" + message);
  }

  public static GatewayException removeFail(String message) {
    return show(REMOVE_FAIL + "，" + message);
  }

  public static GatewayException removeFail(String message, Object data) {
    return show(REMOVE_FAIL + "，" + message, data);
  }

  public static GatewayException removeFail(int code, String message, Throwable throwable) {
    return show(code, REMOVE_FAIL + "，" + message, throwable);
  }

  public static GatewayException removeFail(String message, Throwable throwable) {
    return show(REMOVE_FAIL + "，" + message, throwable);
  }

  public static void ignored(Supplier<Void> supplier) {
    try {
      supplier.get();
    } catch (Exception ignored) {
    }
  }
}
