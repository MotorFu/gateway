/*
 * This file is generated by jOOQ.
 */
package com.motorfu.gateway.jooq.tables;


import com.motorfu.gateway.jooq.GatewayTable;
import com.motorfu.gateway.jooq.Indexes;
import com.motorfu.gateway.jooq.Keys;
import com.motorfu.gateway.jooq.tables.records.RoutesRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row12;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * 网关路由
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RoutesTable extends TableImpl<RoutesRecord> {

    private static final long serialVersionUID = 1549981434;

    /**
     * The reference instance of <code>gateway.routes</code>
     */
    public static final RoutesTable ROUTES = new RoutesTable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RoutesRecord> getRecordType() {
        return RoutesRecord.class;
    }

    /**
     * The column <code>gateway.routes.id</code>. 路由ID
     */
    public final TableField<RoutesRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "路由ID");

    /**
     * The column <code>gateway.routes.name</code>. 名称即routeId
     */
    public final TableField<RoutesRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "名称即routeId");

    /**
     * The column <code>gateway.routes.uri</code>. 代理路径
     */
    public final TableField<RoutesRecord, String> URI = createField(DSL.name("uri"), org.jooq.impl.SQLDataType.VARCHAR(128).nullable(false), this, "代理路径");

    /**
     * The column <code>gateway.routes.filters</code>. 对应RouteDefinition类中的filters
     */
    public final TableField<RoutesRecord, String> FILTERS = createField(DSL.name("filters"), org.jooq.impl.SQLDataType.CLOB, this, "对应RouteDefinition类中的filters");

    /**
     * The column <code>gateway.routes.predicates</code>. 对应RouteDefinition类中的predicates
     */
    public final TableField<RoutesRecord, String> PREDICATES = createField(DSL.name("predicates"), org.jooq.impl.SQLDataType.CLOB, this, "对应RouteDefinition类中的predicates");

    /**
     * The column <code>gateway.routes.metadata</code>. 对应RouteDefinition类中的metadata
     */
    public final TableField<RoutesRecord, String> METADATA = createField(DSL.name("metadata"), org.jooq.impl.SQLDataType.CLOB, this, "对应RouteDefinition类中的metadata");

    /**
     * The column <code>gateway.routes.order</code>. 顺序
     */
    public final TableField<RoutesRecord, Integer> ORDER = createField(DSL.name("order"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "顺序");

    /**
     * The column <code>gateway.routes.deleted</code>. 是否删除
     */
    public final TableField<RoutesRecord, Boolean> DELETED = createField(DSL.name("deleted"), org.jooq.impl.SQLDataType.BIT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("b'0'", org.jooq.impl.SQLDataType.BIT)), this, "是否删除");

    /**
     * The column <code>gateway.routes.created</code>. 记录创建时间
     */
    public final TableField<RoutesRecord, Long> CREATED = createField(DSL.name("created"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.BIGINT)), this, "记录创建时间");

    /**
     * The column <code>gateway.routes.last_modified</code>. 记录更新时间
     */
    public final TableField<RoutesRecord, Long> LAST_MODIFIED = createField(DSL.name("last_modified"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.BIGINT)), this, "记录更新时间");

    /**
     * The column <code>gateway.routes.created_by</code>. 记录创建人
     */
    public final TableField<RoutesRecord, String> CREATED_BY = createField(DSL.name("created_by"), org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "记录创建人");

    /**
     * The column <code>gateway.routes.last_modified_by</code>. 记录更新人
     */
    public final TableField<RoutesRecord, String> LAST_MODIFIED_BY = createField(DSL.name("last_modified_by"), org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "记录更新人");

    /**
     * Create a <code>gateway.routes</code> table reference
     */
    public RoutesTable() {
        this(DSL.name("routes"), null);
    }

    /**
     * Create an aliased <code>gateway.routes</code> table reference
     */
    public RoutesTable(String alias) {
        this(DSL.name(alias), ROUTES);
    }

    /**
     * Create an aliased <code>gateway.routes</code> table reference
     */
    public RoutesTable(Name alias) {
        this(alias, ROUTES);
    }

    private RoutesTable(Name alias, Table<RoutesRecord> aliased) {
        this(alias, aliased, null);
    }

    private RoutesTable(Name alias, Table<RoutesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("网关路由"), TableOptions.table());
    }

    public <O extends Record> RoutesTable(Table<O> child, ForeignKey<O, RoutesRecord> key) {
        super(child, key, ROUTES);
    }

    @Override
    public Schema getSchema() {
        return GatewayTable.GATEWAY;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ROUTES_IDX_NAME);
    }

    @Override
    public Identity<RoutesRecord, Long> getIdentity() {
        return Keys.IDENTITY_ROUTES;
    }

    @Override
    public UniqueKey<RoutesRecord> getPrimaryKey() {
        return Keys.KEY_ROUTES_PRIMARY;
    }

    @Override
    public List<UniqueKey<RoutesRecord>> getKeys() {
        return Arrays.<UniqueKey<RoutesRecord>>asList(Keys.KEY_ROUTES_PRIMARY);
    }

    @Override
    public RoutesTable as(String alias) {
        return new RoutesTable(DSL.name(alias), this);
    }

    @Override
    public RoutesTable as(Name alias) {
        return new RoutesTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RoutesTable rename(String name) {
        return new RoutesTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RoutesTable rename(Name name) {
        return new RoutesTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row12 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row12<Long, String, String, String, String, String, Integer, Boolean, Long, Long, String, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }
}
