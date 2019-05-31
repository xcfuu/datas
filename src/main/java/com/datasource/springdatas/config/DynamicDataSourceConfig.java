package com.datasource.springdatas.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuechaofu
 * @date 2019/5/31 13:58
 */
public class DynamicDataSourceConfig {
    @ConfigurationProperties("dynamic.jdbc")
    @Data
    public static class DynamicDataSourceProperties {

        private JdbcDataSourceProperties[] datasources;
    }

    /**
     * 根据配置文件生成动态数据源
     * @param dynamicDataSourceProperties
     * @return
     */
    @Bean("dynamicDataSource")
    public DataSource dataSource(DynamicDataSourceProperties dynamicDataSourceProperties){
        JdbcDataSourceProperties[] jdbcDataSourceProperties=dynamicDataSourceProperties.getDatasources();
        if(jdbcDataSourceProperties==null){
            throw new IllegalArgumentException("can not find any available dynamic.datasource config");
        }
        Map<Object, Object> targetDataSources = new HashMap<>();
        for(int i=0;i<jdbcDataSourceProperties.length;i++){
            JdbcDataSourceProperties properties =jdbcDataSourceProperties[i];
            if (properties.getDatasourceId() == null || properties.getDatasourceId().trim().isEmpty()) {
                throw new IllegalArgumentException("dynamic.jdbc.datasources[" + i + "].datasourceId can not be null");
            }
            if (targetDataSources.get(properties.getDatasourceId()) != null) {
                throw new IllegalArgumentException("dynamic.jdbc.datasources[" + i + "].datasourceId already exists");
            }
            properties.setType(HikariDataSource.class);
            targetDataSources.put(properties.getDatasourceId(),properties.initializeDataSourceBuilder().build());
        }
        MultipleDataSourceToChoose multipleDataSource = new MultipleDataSourceToChoose();
        multipleDataSource.setTargetDataSources(targetDataSources);

        return null;
    }


}
