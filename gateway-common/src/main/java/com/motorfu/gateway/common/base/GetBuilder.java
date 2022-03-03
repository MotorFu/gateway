package com.motorfu.gateway.common.base;

import org.jooq.UpdatableRecord;
import org.jooq.impl.TableImpl;

import java.util.Optional;

/**
 * @author fufengming
 * @email ffu@leapcloud.cn
 * @since 2020/11/9 13:59
 */
public class GetBuilder<R extends UpdatableRecord<R>, T extends TableImpl<R>, P> {

  private final AbstractDao<R,T, P> dao;
  private final QueryBuilder<R,T,P> query;

  public GetBuilder(AbstractDao<R,T, P> dao, QueryBuilder<R,T,P> query) {
    this.dao = dao;
    this.query=query;
  }

  public Optional<P> build() {
    return query.select().fetchOptionalInto(dao.type());
  }

  public <E> Optional<E> build(Class<E> tClass) {
    return query.customSelect().fetchOptionalInto(tClass);
  }
}
