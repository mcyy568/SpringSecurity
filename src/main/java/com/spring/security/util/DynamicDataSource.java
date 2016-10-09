package com.spring.security.util;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 创建用户：杨辽
 * 创建时间：2016-09-27 14:38:00
 * 描    述：
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public static final String DATA_SOURCE_MASTER = "dataSourceMaster";
    public static final String DATA_SOURCE_SLAVE = "dataSourceSlave";
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }
    public static String getCustomerType() {
        return contextHolder.get();
    }
    public static void clearCustomerType() {
        contextHolder.remove();
    }
    @Override
    public Logger getParentLogger() {
        return null;
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return getCustomerType();
    }

}

