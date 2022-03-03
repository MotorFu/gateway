package com.motorfu.gateway.common.builder;

import com.motorfu.gateway.common.utils.JsonUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author motorfu
 * @email ffu@leapcloud.cn
 * @since 2018/8/31 12:04
 */
@Setter
@Getter
@ToString
public class WhereCondition {

  private Condition condition;

  public WhereCondition(Builder builder) {
    this.condition = builder.condition;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * 默认附加 1=1 条件 防止条件拼接时 null 判断
   *
   * @param attachCondition
   * @return
   */
  public static Builder newBuilder(boolean attachCondition) {
    return new Builder(attachCondition ? DSL.condition("1=1") : null);
  }

  public static Builder newBuilder(Condition condition) {
    return new Builder(condition);
  }

  public static class Builder {
    private Condition condition;

    public Builder() {
    }

    public Builder(Condition condition) {
      this.condition = condition;
    }

    public Builder condition(Condition condition) {
      if (condition == null) {
        return this;
      }
      if (this.condition == null) {
        this.condition = condition;
      } else {
        this.condition = this.condition.and(condition);
      }
      return this;
    }

    public <T> Builder eq(Field<T> field, T value) {
      if (value == null) {
        return this;
      }
      return condition(field.eq(value));
    }


    public Builder and(Condition condition) {
      return condition(condition);
    }

    /**
     * 不允许 null
     *
     * @param field
     * @param value
     * @param <T>
     * @return
     */
    public <T> Builder andEq(Field<T> field, T value) {
      if (value == null) {
        return this;
      }
      return and(field.eq(value));
    }

    /**
     * 不允许 null or empty
     *
     * @param field
     * @param value
     * @param <T>
     * @return
     */
    public <T> Builder andEqNotEmpty(Field<T> field, T value) {
      if (StringUtils.isEmpty(value)) {
        return this;
      }
      return and(field.eq(value));
    }

    public <T> Builder andLike(Field<T> field, String value) {
      if (value == null) {
        return this;
      }
      return and(field.like(value));
    }

    public <T> Builder andLikePrefix(Field<T> field, String value) {
      if (value == null) {
        return this;
      }
      return and(field.like("%" + value));
    }

    public <T> Builder andLikeSuffix(Field<T> field, String value) {
      if (value == null) {
        return this;
      }
      return and(field.like(value + "%"));
    }

    public <T> Builder andLikeBoth(Field<T> field, String value) {
      if (value == null) {
        return this;
      }
      return and(field.like("%" + value + "%"));
    }

    public <T> Builder andLikeBothNotEmpty(Field<T> field, String value) {
      if (StringUtils.isEmpty(value)) {
        return this;
      }
      return and(field.like("%" + value + "%"));
    }

    public Builder or(Condition condition) {
      if (condition == null) {
        return this;
      }
      if (this.condition == null) {
        this.condition = condition;
      } else {
        this.condition = this.condition.or(condition);
      }
      return this;
    }

    public <T> Builder orEq(Field<T> field, T value) {
      if (value == null) {
        return this;
      }
      return or(field.eq(value));
    }

    public <T> Builder orEqNotEmpty(Field<T> field, T value) {
      if (StringUtils.isEmpty(value)) {
        return this;
      }
      return or(field.eq(value));
    }


    public <T> Builder orLikePrefix(Field<T> field, String value) {
      if (value == null) {
        return this;
      }
      return or(field.like("%" + value));
    }

    public <T> Builder orLikeSuffix(Field<T> field, String value) {
      if (value == null) {
        return this;
      }
      return or(field.like(value + "%"));
    }

    public <T> Builder orLikeBoth(Field<T> field, String value) {
      if (value == null) {
        return this;
      }
      return or(field.like("%" + value + "%"));
    }

    public <T> Builder orLikeBothNotEmpty(Field<T> field, String value) {
      if (StringUtils.isEmpty(value)) {
        return this;
      }
      return or(field.like("%" + value + "%"));
    }

    public <T> Builder orIn(Field<T> field, List<T> values) {
      if (CollectionUtils.isEmpty(values)) {
        return this;
      }
      return or(field.in(values));
    }

    public <T> Builder andIn(Field<T> field, List<T> values) {
      if (CollectionUtils.isEmpty(values)) {
        return this;
      }
      return and(field.in(values));
    }

    public WhereCondition build() {
      return new WhereCondition(this);
    }

    public String toJson() {
      return JsonUtils.encode(build());
    }
  }
}
