package com.datasource.springdatas.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author xuechaofu
 * @date 2019/5/30 20:17
 */
public class MultipleDataSourceToChoose extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return HandlerDataSource.getDataSource();
    }
}
