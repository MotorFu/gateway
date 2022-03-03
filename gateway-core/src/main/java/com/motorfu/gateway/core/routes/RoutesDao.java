package com.motorfu.gateway.core.routes;

import com.motorfu.gateway.common.base.AbstractDao;
import com.motorfu.gateway.jooq.tables.RoutesTable;
import com.motorfu.gateway.jooq.tables.pojos.RoutesEntity;
import com.motorfu.gateway.jooq.tables.records.RoutesRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fufengming
 * @email ffu@leapcloud.cn
 * @since 2020/11/9 11:33
 */
@Service
public class RoutesDao extends AbstractDao<RoutesRecord, RoutesTable, RoutesEntity> {

  @Autowired
  protected RoutesDao(DSLContext dsl) {
    super(dsl);
  }

  @Override
  public RoutesTable table() {
    return RoutesTable.ROUTES;
  }

  @Override
  protected Class<RoutesEntity> type() {
    return RoutesEntity.class;
  }
}
