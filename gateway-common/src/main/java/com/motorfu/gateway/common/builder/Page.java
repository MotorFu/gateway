package com.motorfu.gateway.common.builder;

import com.motorfu.gateway.common.model.BaseEntity;
import com.motorfu.gateway.common.model.query.PageQuery;
import com.motorfu.gateway.common.utils.JsonUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jooq.Condition;
import org.jooq.SortField;
import org.jooq.SortOrder;
import org.jooq.impl.DSL;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author motorfu
 * @email ffu@leapcloud.cn
 * @since 2018/8/29 16:39
 */
@Setter
@Getter
@ToString
public class Page extends BaseEntity {
  private int pageNum = 1;
  private int pageSize = 15;

  private Condition condition;

  private List<SortField<?>> orderFields;

  public Page(Builder builder) {
    this.pageNum = builder.pageNum;
    this.pageSize = builder.pageSize;
    this.orderFields = builder.orderFields;
    this.condition = builder.condition;
  }

  public static Builder newBuilder(PageQuery pageQuery) {
    return new Builder(pageQuery);
  }

  public static Builder newBuilder(PageQuery pageQuery, Condition condition) {
    return new Builder(pageQuery, condition);
  }

  public static Builder newBuilder(PageQuery pageQuery, WhereCondition whereCondition) {
    return new Builder(pageQuery, whereCondition);
  }


  public static class Builder {
    private int pageNum = 1;
    private int pageSize = 15;

    private Condition condition;

    private List<SortField<?>> orderFields;

    public Builder(PageQuery pageQuery) {
      this.pageNum = pageQuery.getPageNum();
      this.pageSize = pageQuery.getPageSize();
      this.orderFields = pageQuery.getOrderFields();
      //处理排序
      List<PageQuery.Order> orderList = pageQuery.getOrderFields();
      if (!CollectionUtils.isEmpty(orderList)) {
        this.orderFields = new ArrayList<>();
        orderList.forEach(order -> {
          SortOrder sortOrder = SortOrder.DEFAULT;
          if (order.getSort() == PageQuery.Sort.DESC) {
            sortOrder = SortOrder.DESC;
          } else if (order.getSort() == PageQuery.Sort.ASC) {
            sortOrder = SortOrder.ASC;
          }
          this.orderFields.add(DSL.field(order.getName()).sort(sortOrder));
        });
      }
    }

    public Builder(PageQuery pageQuery, Condition condition) {
      this(pageQuery);
      this.condition = condition;
    }

    public Builder(PageQuery pageQuery, WhereCondition whereCondition) {
      this(pageQuery);
      this.condition = whereCondition.getCondition();
    }

    public Builder condition(Condition condition) {
      this.condition = condition;
      return this;
    }

    public Builder condition(WhereCondition whereCondition) {
      this.condition = whereCondition.getCondition();
      return this;
    }

    public Page build() {
      return new Page(this);
    }

    public String toJson() {
      return JsonUtils.encode(new Page(this));
    }
  }
}
