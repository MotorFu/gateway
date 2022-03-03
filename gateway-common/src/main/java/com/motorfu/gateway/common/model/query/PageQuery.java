package com.motorfu.gateway.common.model.query;

import com.motorfu.gateway.common.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author motorfu
 * @email ffu@leapcloud.cn
 * @since 2018/8/29 16:39
 */
@Setter
@Getter
@ToString
public class PageQuery<T> extends BaseQuery {
//  @Max(value = Integer.MAX_VALUE, message = "页数超过最大值")
  private int pageNum = 1;
//  @Max(value = 500, message = "每页条数最多为500条")
  private int pageSize = 15;

  private List<Order> orderFields;

//  @Valid
  private T params;


  @Setter
  @Getter
  @ToString
  public static class Order {
    private String name;
    private Sort sort = Sort.DEFAULT;
  }

  public enum Sort {
    DEFAULT,
    DESC,
    ASC
  }

}
