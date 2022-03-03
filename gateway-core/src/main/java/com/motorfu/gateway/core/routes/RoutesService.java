package com.motorfu.gateway.core.routes;

import com.motorfu.gateway.jooq.tables.pojos.RoutesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author fufengming
 * @email ffu@leapcloud.cn
 * @since 2020/11/9 10:04
 */
@Service
public class RoutesService {
  private final RoutesDao dao;

  @Autowired
  public RoutesService(RoutesDao dao) {
    this.dao = dao;
  }

  public Optional<RoutesEntity> get(Long id){
    return this.dao.query()
      .condition(this.dao.table().ID.eq(id))
      .get()
      .build();
  }
}
