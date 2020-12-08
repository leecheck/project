package com.gytech.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by LQ on 2018/6/29.
 * i.lq.web.dataSource
 */
public class DsUtil {

    private static final Logger logger = LoggerFactory.getLogger(DsUtil.class);

    public static DruidDataSource BuildDataSource(DruidDataSourceProperty properties){
        DruidDataSource dataSource = new DruidDataSource();
        if (properties == null)
            return null;
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        if (properties.getInitialSize() > 0) {
            dataSource.setInitialSize(properties.getInitialSize());
            logger.info("setInitialSize --->" + properties.getInitialSize());
        }
        if (properties.getMinIdle() > 0) {
            dataSource.setMinIdle(properties.getMinIdle());
            logger.info("setInitialSize --->" + properties.getInitialSize());
        }
        if (properties.getMaxActive() > 0) {
            dataSource.setMaxActive(properties.getMaxActive());
            logger.info("setMaxActive --->" + properties.getMaxActive());
        }
        if (properties.getTestOnBorrow() != null) {
            dataSource.setTestOnBorrow(properties.getTestOnBorrow());
            logger.info("setTestOnBorrow --->" + properties.getTestOnBorrow());
        }
        if (properties.getMaxWait() > 0) {
            dataSource.setMaxWait(properties.getMaxWait());
            logger.info("setMaxWait --->" + properties.getMaxWait());
        }
        if (properties.getTimeBetweenEvictionRunsMillis() > 0) {
            dataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
            logger.info("setTimeBetweenEvictionRunsMillis --->" + properties.getTimeBetweenEvictionRunsMillis());
        }
        if (properties.getMinEvictableIdleTimeMillis() > 0) {
            dataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
            logger.info("setMinEvictableIdleTimeMillis --->" + properties.getMinEvictableIdleTimeMillis());
        }
        if (properties.getValidationQuery() != null) {
            dataSource.setValidationQuery(properties.getValidationQuery());
            logger.info("setValidationQuery --->" + properties.getValidationQuery());
        }
        if (properties.getTestWhileIdle() != null) {
            dataSource.setTestWhileIdle(properties.getTestWhileIdle());
            logger.info("setTestWhileIdle --->" + properties.getTestWhileIdle());
        }
        if (properties.getTestOnReturn() != null) {
            dataSource.setTestOnReturn(properties.getTestOnReturn());
            logger.info("setTestOnReturn --->" + properties.getTestOnReturn());
        }
        if (properties.getPoolPreparedStatements() != null) {
            dataSource.setPoolPreparedStatements(properties.getPoolPreparedStatements());
            logger.info("setPoolPreparedStatements --->" + properties.getPoolPreparedStatements());
        }
        if (properties.getMaxPoolPreparedStatementPerConnectionSize() > 0) {
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());
            logger.info("setMaxPoolPreparedStatementPerConnectionSize --->" + properties.getMaxPoolPreparedStatementPerConnectionSize());
        }
        if (properties.getFilters() != null) {
            try {
                dataSource.setFilters(properties.getFilters());
                logger.info("setFilters --->" + properties.getFilters());
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("setInitialSize error");
            }
        }
        if (properties.getConnectionProperties() != null) {
            dataSource.setConnectionProperties(properties.getConnectionProperties());
            logger.info("setConnectionProperties --->" + properties.getConnectionProperties());
        }
        try {
            dataSource.init();
            logger.info("dataSource.init()");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }
}
