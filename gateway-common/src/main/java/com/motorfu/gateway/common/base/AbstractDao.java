package com.motorfu.gateway.common.base;

import com.motorfu.gateway.common.builder.Page;
import com.motorfu.gateway.common.model.PageInfo;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author motorfu
 * @email ffu@leapcloud.cn
 * @since 2019-01-22 13:34
 */
public abstract class AbstractDao<R extends UpdatableRecord<R>, T extends TableImpl<R>, P> {


  protected final DSLContext dsl;

  public abstract T table();

  protected abstract Class<P> type();

  protected AbstractDao(DSLContext dsl) {
    this.dsl = dsl;
  }


  public DSLContext dsl() {
    return this.dsl;
  }

  public Field<Long> id() {
    return table().field("id").coerce(Long.class);
  }


  public Field<Boolean> deleted() {
    return table().field("deleted").coerce(Boolean.class);
  }

  public Field<Long> lastModified() {
    return table().field("last_modified").coerce(Long.class);
  }

  public Field<String> lastModifiedBy() {
    return table().field("last_modified_by").coerce(String.class);
  }

  public Field<Long> created() {
    return table().field("created").coerce(Long.class);
  }

  public Field<String> createdBy() {
    return table().field("created_by").coerce(String.class);
  }

  public String getPrincipal() {
    try {
      String name = "system";
//      Object account = CommonConst.ACCOUNT_TOKENS.get(UbasContext.current().getToken());
//      if (account != null) {
//        Map<String, Object> map = JsonUtils.encodeMap(account);
//        if (map != null) {
//          name = TransformUtils.getValue4Map(map, "realname", "");
//          if (StringUtils.isEmpty(name)) {
//            name = TransformUtils.getValue4Map(map, "username", "");
//          }
//        }
//      }
      return name;
    } catch (Exception e) {
      return "system";
    }
  }

  /**
   * select 合成方法， 包含是否删除条件
   *
   * @return
   */
//
  public SelectConditionStep<R> select() {
    return this.dsl.selectFrom(table())
      .where(deleted().eq(false));
  }

  /**
   * 查询返回指定字段
   *
   * @param selectFields
   * @return
   */

  protected SelectConditionStep<?> select(SelectField<?>... selectFields) {
    return this.dsl.select(selectFields).from(table())
      .where(deleted().eq(false));
  }

  /**
   * 根据查询条件
   * 查询返回指定字段
   *
   * @param condition
   * @param selectFields
   * @return
   */

  protected SelectConditionStep<?> select(Condition condition, SelectField<?>... selectFields) {
    if (condition == null) {
      throw new GatewayException("查询条件不能为空");
    }
    return select(selectFields).and(condition);
  }

  public QueryBuilder<R,T,P> query() {
    return new QueryBuilder<>(this);
  }


  /**
   * update 合成方法， 包含是否删除条件
   *
   * @param entity
   * @return
   */

  protected UpdateConditionStep<R> update(P entity) {
    R record = this.dsl.newRecord(table(), entity);
    return update(record);
  }

  protected UpdateConditionStep<R> update(R record) {
    Long id = record.get(id());
    if (id == null) {
      throw new GatewayException("更新数据 ID 不能为空");
    }
    record.set(lastModified(), System.currentTimeMillis());
    record.set(lastModifiedBy(), getPrincipal());
    return this.dsl.update(table())
      .set(record).where(deleted().eq(false).and(id().eq(id)));
  }

  /**
   * 添加数据
   *
   * @param entity
   * @return
   */

  public Optional<P> create(P entity) {
    R record = dsl().newRecord(table(), entity);
    record.set(created(), System.currentTimeMillis());
    record.set(createdBy(), getPrincipal());
    return Optional.ofNullable(dsl().insertInto(table()).set(record).returning().fetchOne()).map(it -> it.into(type()));
  }


  public List<Long> findIds(Condition condition) {
    if (condition == null) {
      throw new GatewayException("查询条件不能为空");
    }
    return select(id()).and(condition).fetchInto(Long.class);
  }


  public List<Long> findIds(Condition condition, int limit) {
    if (condition == null) {
      throw new GatewayException("查询条件不能为空");
    }
    return select(id()).and(condition).limit(limit).fetchInto(Long.class);
  }


  public List<Long> findIds(int limit) {
    return select(id()).limit(limit).fetchInto(Long.class);
  }


  public Long findId(Condition condition) {
    if (condition == null) {
      throw new GatewayException("查询条件不能为空");
    }
    return select(id()).and(condition).fetchOneInto(Long.class);
  }

  public boolean exist(Condition condition) {
    if (condition == null) {
      throw new GatewayException("查询条件不能为空");
    }
    return this.dsl.fetchExists(table(), condition);
  }

  public boolean exist(String key, Object value) {
    Objects.requireNonNull(key, "字段不能为空");
    return this.dsl.fetchExists(table(), DSL.field(key).eq(value));
  }

  public <F> boolean exist(Field<F> key, F value) {
    Objects.requireNonNull(key, "字段不能为空");
    return this.dsl.fetchExists(table(), key.eq(value));
  }

  public <F1, F2> boolean exist(Field<F1> k1, F1 v1, Field<F2> k2, F2 v2) {
    Objects.requireNonNull(k1, "k1字段不能为空");
    Objects.requireNonNull(k2, "k2字段不能为空");
    return this.dsl.fetchExists(table(), k1.eq(v1).and(k2.eq(v2)));
  }


  /**
   * 删除数据 可恢复
   *
   * @param id
   * @return
   */

  public int remove(Long id) {
    return removes(id);
  }

  /**
   * 批量删除数据
   *
   * @param ids
   * @return
   */

  public int removes(List<Long> ids) {
    if (ids == null || ids.size() <= 0) {
      return 0;
    }
    return removes(ids.toArray(new Long[0]));
  }

  /**
   * 批量删除数据
   *
   * @param ids
   * @return
   */

  public int removes(Long... ids) {
    if (ids == null || ids.length <= 0) {
      return 0;
    }
    return this.dsl.update(table())
      .set(deleted(), true)
      .set(lastModified(), System.currentTimeMillis())
      .set(lastModifiedBy(), getPrincipal())
      .where(deleted().eq(false)
        .and(id().in(ids)))
      .execute();
  }

  /**
   * 删除数据，无法恢复
   *
   * @param id@return
   */

  public int delete(Long id) {
    return this.dsl.deleteFrom(table()).where(id().eq(id)).execute();
  }

  public int delete(Condition condition) {
    return this.dsl.deleteFrom(table()).where(condition).execute();
  }

  /**
   * 更新数据
   *
   * @param entity
   * @return
   */

  public Optional<P> modify(P entity) {
    R record = this.dsl.newRecord(table(), entity);
    Long id = record.get(id());
    if (id == null) {
      throw new GatewayException("更新数据 ID 不能为空");
    }
    handleNullField(record);
    int result = update(record).execute();
    if (result > 0) {
      return query().get().build();
    }
    return Optional.empty();
  }

  /**
   * 分页获取数据
   *
   * @param pageNum  >=1
   * @param pageSize <=500
   * @return
   */

  public PageInfo<P> paging(int pageNum, int pageSize) {
    int totalSize = this.dsl.fetchCount(select());

    PageInfo<P> pageInfo = new PageInfo<>(pageNum, pageSize, totalSize);
    int offset = (pageInfo.getPageNum() - 1) * pageInfo.getPageSize();

    List<P> result = select().limit(offset, pageInfo.getPageSize()).fetchInto(type());
    pageInfo.setList(result);
    return pageInfo;
  }


  public PageInfo<P> paging(Page page) {
    SelectConditionStep<?> select = select();
    if (page.getCondition() != null) {
      select.and(page.getCondition());
    }
    //计算总数
    int totalSize = this.dsl.fetchCount(select);
    if (!CollectionUtils.isEmpty(page.getOrderFields())) {
      select.orderBy(page.getOrderFields());
    }
    PageInfo<P> pageInfo = new PageInfo<>(page.getPageNum(), page.getPageSize(), totalSize);
    int offset = (pageInfo.getPageNum() - 1) * pageInfo.getPageSize();

    List<P> result = select.limit(offset, pageInfo.getPageSize()).fetchInto(type());
    pageInfo.setList(result);
    return pageInfo;
  }

  /**
   * 过滤null值的属性
   *
   * @param record
   */
  private void handleNullField(Record record) {
    Stream.of(record.fields()).filter(field -> record.getValue(field) == null).forEach(field -> record.changed(field, false));
  }


}
