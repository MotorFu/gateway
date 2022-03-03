/*
 * This file is generated by jOOQ.
 */
package com.motorfu.gateway.jooq;


import com.motorfu.gateway.jooq.tables.RoutesTable;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GatewayTable extends SchemaImpl {

    private static final long serialVersionUID = 1947457738;

    /**
     * The reference instance of <code>gateway</code>
     */
    public static final GatewayTable GATEWAY = new GatewayTable();

    /**
     * 网关路由
     */
    public final RoutesTable ROUTES = RoutesTable.ROUTES;

    /**
     * No further instances allowed
     */
    private GatewayTable() {
        super("gateway", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            RoutesTable.ROUTES);
    }
}
