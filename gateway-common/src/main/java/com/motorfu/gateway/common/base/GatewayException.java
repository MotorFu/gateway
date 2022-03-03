package com.motorfu.gateway.common.base;

/**
 * @author fufengming
 * @email ffu@leapcloud.cn
 * @since 2020/11/6 18:18
 */
public class GatewayException extends RuntimeException {
  private int code = 400;
  private String msg;
  private Object data;

  public GatewayException(String msg) {
    super(msg);
    this.msg = msg;
  }

  public GatewayException(Throwable cause) {
    super(cause.getMessage(), cause);
    this.msg = cause.getMessage();
  }

  public GatewayException(int code, String msg) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }

  public GatewayException(String msg,Object data) {
    super(msg);
    this.data = data;
    this.msg=msg;
  }

  public GatewayException(int code, String msg, Object data) {
    super(msg);
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public GatewayException(int code, Throwable cause) {
    super(cause);
    this.code = code;
    this.msg = cause.getMessage();
  }

  public GatewayException(String message, Throwable cause) {
    super(message, cause);
    this.msg = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
