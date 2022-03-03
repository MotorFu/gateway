package com.motorfu.gateway.common.base;

import org.jooq.UpdatableRecord;
import org.jooq.impl.TableImpl;

import java.util.List;

/**
 * @author fufengming
 * @email ffu@leapcloud.cn
 * @since 2020/11/9 13:59
 */
public class FindBuilder<R extends UpdatableRecord<R>, T extends TableImpl<R>, P> {
  private final AbstractDao<R,T, P> dao;
  private final QueryBuilder<R,T,P> query;
  public FindBuilder(AbstractDao<R, T, P> dao, QueryBuilder<R, T, P> query) {
    this.dao = dao;
    this.query = query;
  }

  public List<P> build() {
    return query.select().fetchInto(dao.type());
  }

  public <E> List<E> build(Class<E> tClass) {
    return query.customSelect().fetchInto(tClass);
  }
}
