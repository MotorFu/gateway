package com.motorfu.gateway.common.base;

import org.jooq.*;
import org.jooq.impl.TableImpl;

import java.util.List;

/**
 * @author fufengming
 * @email ffu@leapcloud.cn
 * @since 2020/11/9 16:44
 */
public class QueryBuilder<R extends UpdatableRecord<R>, T extends TableImpl<R>, P> {
  private final AbstractDao<R, T, P> dao;


  protected Condition condition;
  protected List<SelectField<?>> returnList;

  public QueryBuilder(AbstractDao<R, T, P> dao) {
    this.dao = dao;
  }

  public QueryBuilder<R, T, P> condition(Condition condition) {
    this.condition = condition;
    return this;
  }

  public QueryBuilder<R, T, P> returnList(List<SelectField<?>> returnList) {
    this.returnList = returnList;
    return this;
  }

  public SelectConditionStep<R> select() {
    Condition newCondition = dao.deleted().eq(false);
    if (condition != null) {
      newCondition = newCondition.and(condition);
    }
    return dao.dsl().selectFrom(dao.table()).where(newCondition);
  }

  public SelectConditionStep<Record> customSelect() {
    Condition newCondition = dao.deleted().eq(false);
    if (condition != null) {
      newCondition = newCondition.and(condition);
    }
    return dao.dsl().select(returnList).from(dao.table()).where(newCondition);
  }

  public GetBuilder<R, T, P> get() {
    return new GetBuilder<>(this.dao, this);
  }

  public FindBuilder<R, T, P> find() {
    return new FindBuilder<>(this.dao, this);
  }
}
