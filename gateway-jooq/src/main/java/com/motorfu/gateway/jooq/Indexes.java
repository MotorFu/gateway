/*
 * This file is generated by jOOQ.
 */
package com.motorfu.gateway.jooq;


import com.motorfu.gateway.jooq.tables.RoutesTable;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>gateway</code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index ROUTES_IDX_NAME = Indexes0.ROUTES_IDX_NAME;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index ROUTES_IDX_NAME = Internal.createIndex("IDX_NAME", RoutesTable.ROUTES, new OrderField[] { RoutesTable.ROUTES.NAME }, false);
    }
}
