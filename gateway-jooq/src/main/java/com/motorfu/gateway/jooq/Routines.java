/*
 * This file is generated by jOOQ.
 */
package com.motorfu.gateway.jooq;


import com.motorfu.gateway.jooq.routines.AddcolumnTable;

import org.jooq.Configuration;


/**
 * Convenience access to all stored procedures and functions in gateway
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Routines {

    /**
     * Call <code>gateway.addColumn</code>
     */
    public static void addcolumn(Configuration configuration, String tn, String cn, String content) {
        AddcolumnTable p = new AddcolumnTable();
        p.setTn(tn);
        p.setCn(cn);
        p.setContent(content);

        p.execute(configuration);
    }
}
